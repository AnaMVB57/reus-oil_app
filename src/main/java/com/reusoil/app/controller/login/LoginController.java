package com.reusoil.app.controller.login;

import com.reusoil.app.models.registro.RegistroDTO;
import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.models.usuario.UsuarioAPI;
import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.services.perfil.PerfilService;
import com.reusoil.app.services.persona.PersonaService;
import com.reusoil.app.services.usuario.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;
    private final PersonaService personaService;


    @PostMapping("/registrar")
    public String registrarUsuario(@Valid @ModelAttribute("registroDTO") RegistroDTO registroDTO,
                                   BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Por favor, corrija los errores en el formulario");
            return "vistas/inicio/login";
        }

        // Validar que el nombre de usuario no esté duplicado
        Optional<UsuarioEntity> usuarioExistente = usuarioService.obtenerUsuarioPorUsuario(registroDTO.getUsuario());
        if (usuarioExistente.isPresent()) {
            bindingResult.rejectValue("usuario", "error.usuario", "Ya existe un usuario con ese nombre.");
            model.addAttribute("registroDTO", registroDTO);
            return "vistas/inicio/login";
        }

        // Crear entidades de Usuario y Persona
        // Crear entidades de Usuario y Persona
        UsuarioEntity usuario = UsuarioEntity.builder()
                .usuario(registroDTO.getUsuario())
                .clave(registroDTO.getClave())
                .estado(true)
                .build();

        usuarioService.guardarUsuario(usuario);

        PersonaEntity persona = PersonaEntity.builder()
                .id(registroDTO.getId())
                .nombre(registroDTO.getNombre())
                .correo(registroDTO.getCorreo())
                .telefono(registroDTO.getTelefono())
                .usuario(usuario)
                .estado(true)
                .build();

        personaService.guardarPersona(persona);

// Guardar usuario y persona en la base de datos

        return "redirect:/mostrar-login";
    }


    @PostMapping("/login")
    public String iniciarSesion(@RequestParam("usuarioLogin") String usuario,
                                @RequestParam("claveLogin") String clave,
                                Model model, HttpServletRequest request) {

        var usuarioActual = usuarioService.obtenerUsuarioPorUsuario(usuario);

        // Verifica si los campos están vacíos
        if (usuario.isEmpty() || clave.isEmpty()) {
            model.addAttribute("usuario", new UsuarioEntity());
            model.addAttribute("error", "Todos los campos son obligatorios.");
            return "vistas/inicio/login";
        }

        if (usuarioActual.isEmpty()) {
            model.addAttribute("usuario", new UsuarioEntity());
            model.addAttribute("error", "Usuario no existe");
            return "vistas/inicio/login";
        }

        UsuarioEntity usuarioEntity = usuarioActual.get();
        if (!clave.equals(usuarioEntity.getClave())) {
            model.addAttribute("usuario", new UsuarioEntity());
            model.addAttribute("error", "Usuario o contraseña incorrectos.");
            return "vistas/inicio/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("usuarioId", usuarioEntity.getId());

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); // Cierra sesión
        return "redirect:/login";
    }

//
//    @PostMapping("/home")
//    public String homePage() {
//        return "vistas/homepage";
//    }

}
