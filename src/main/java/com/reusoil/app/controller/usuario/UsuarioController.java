package com.reusoil.app.controller.usuario;

import com.reusoil.app.models.usuario.UsuarioAPI;
import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.services.usuario.UsuarioServiceImpl;
import com.reusoil.app.utils.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final EmailService emailService;

    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute UsuarioAPI usuario) {
        usuarioService.guardarUsuario(
                UsuarioEntity.builder()
                        .usuario(usuario.getUsuario())
                        .clave(usuario.getClave())
                        .build()
        );
        return "redirect:/mostrar-login";
    }

    @PostMapping("/guardar")
    public String crearOActualizarUsuario(@Valid @ModelAttribute("usuarioGuardar") UsuarioEntity usuarioGuardar,
                                          BindingResult bindingResult,
                                          Model model) {
        // Verificar errores de validación
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Por favor, corrija los errores en el formulario");
            return "vistas/usuario/form_usuario"; // Volver al formulario con errores de validación
        }

        try {
            // Verificar si existe un tipo de sensor con la misma descripción
            Optional<UsuarioEntity> usuarioExistente = usuarioService.obtenerUsuarioPorUsuario(usuarioGuardar.getUsuario());

            // Si se encuentra una descripción duplicada y no es el mismo registro (evitar duplicados en actualización)
            if (usuarioExistente.isPresent() && !usuarioExistente.get().getId().equals(usuarioGuardar.getId())) {
                bindingResult.rejectValue("usuario", "error.usuarioGuardar", "Ya existe un usuario con ese nombre.");
                model.addAttribute("usuarioGuardar", usuarioGuardar); // Mantener datos en el formulario
                return "vistas/usuario/form_usuario"; // Volver al formulario con mensaje de error
            }

            usuarioService.guardarUsuario(usuarioGuardar);
            return "redirect:/usuario/listado-usuarios";

        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al guardar la ciudad");
            model.addAttribute("usuarioGuardar", usuarioGuardar); // Reenviar los datos en caso de error
            return "vistas/usuario/form_usuario"; // Volver al formulario con mensaje de error
        }
    }


    @GetMapping("/usuarioRecuperarContrasena")
    public String recuperarUsuario(Model model) {
        UsuarioEntity usuario = new UsuarioEntity();
        model.addAttribute("usuario", usuario);
        model.addAttribute("mensaje", "");
        return "plantillas/recuperar-clave";
    }

    @PostMapping("/recuperarUsuario")
    public String recuperarContrasena(@RequestParam("correo") String correo, Model model, SessionStatus status) {
        //Usuario usuarioExistente = usuarioService.consultar(email);
        System.out.println("email: "+correo);

        List<UsuarioEntity> usuarios= usuarioService.obtenerUsuariosTodos();
        for (UsuarioEntity usuario : usuarios) {
            if (correo.equals( usuario.getUsuario())) {
                String contrasena = usuario.getClave(); // Obtener la contraseña

                String mensaje = "Su contraseña es: " + contrasena;
                emailService.enviarCorreo(correo, "Recuperación de contraseña", mensaje);

                status.setComplete();
                model.addAttribute("mensaje", "Se ha enviado su contraseña al correo electrónico.");
                System.out.println("Cumplio "+usuario.getUsuario());

                return "plantillas/recuperar-clave";
            }
            System.out.println("No cumplio "+usuario.getUsuario());
        }
        model.addAttribute("error", "No se encontró un usuario con ese email.");
        return "plantillas/recuperar-clave";

    }

    @GetMapping("/{id}")
    public UsuarioEntity obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id).orElse(null);
    }


//    @PutMapping("/actualizar")
//    public UsuarioEntity actualizarUsuario(@RequestBody UsuarioEntity usuarioEntity) {
//        return usuarioService.guardarUsuario(usuarioEntity);
//    }

    // @DeleteMapping("/eliminar/{id}")
    // public void eliminarUsuario(@PathVariable Long id) {
    //     usuarioService.eliminarUsuario(id);
    // }

}
