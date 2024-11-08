package com.reusoil.app.controller.usuario;

import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.services.perfil.PerfilServiceImpl;
import com.reusoil.app.services.usuario.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/usuario")
public class UsuarioVistaController {

    private final UsuarioServiceImpl usuarioService;
    private final PerfilServiceImpl perfilService;

    @GetMapping("/formulario-usuario")
    public String mostrarFormularioUsuario(Model model) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEstado(true); // Inicializa el estado como activo
        model.addAttribute("modoEdicion", false);
        model.addAttribute("perfiles", perfilService.obtenerPerfilesPorEstado(true));
        model.addAttribute("usuarioGuardar", usuario);
        return "vistas/usuario/form_usuario";
    }

    @GetMapping("/listado-usuarios")
    public String mostrarListadoUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.obtenerUsuariosPorEstado(true));
        model.addAttribute("mensaje", "Listado de usuarios");
        return "vistas/usuario/listado_usuario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<UsuarioEntity> usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuarioGuardar", usuario.get());
            model.addAttribute("perfiles", perfilService.obtenerPerfilesPorEstado(true));
            model.addAttribute("modoEdicion", true);
            return "vistas/usuario/form_usuario";
        }
        return "redirect:/usuario/listado-usuarios";
    }
}
