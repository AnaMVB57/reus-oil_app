package com.reusoil.app.controller;

import com.reusoil.app.models.usuario.UsuarioAPI;
import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.services.UsuarioServiceIface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioServiceIface usuarioService;

    @GetMapping("/mostrar-login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("usuarioEntity", new UsuarioAPI());
        return "vistas/login"; // Nombre de la vista de login
    }
    
    // PostMapping que reciba la contraseña y redirija a la vista homepage.html
    @PostMapping("/login")
    public String iniciarSesion(@RequestParam("usuarioLogin") String usuario,
                                @RequestParam("claveLogin") String clave,
                                Model model) {
        var usuarioActual = usuarioService.obtenerUsuarioPorUsuario(usuario);

        if (usuario.isEmpty() || clave.isEmpty()) {
            model.addAttribute("error", "Todos los campos son obligatorios.");
            return "vistas/login";
        }

        if(usuarioActual == null) {
            model.addAttribute("error", "Usuario no existe");
             return "vistas/login";
        }

        if (!usuario.equals(usuarioActual.getUsuario()) || !clave.equals(usuarioActual.getClave())) {
            model.addAttribute("error", "Usuario o contraseña incorrectos.");
            return "vistas/login";
        }
        return "vistas/homepage";
    }

    @PostMapping("/registrar")
    public String registrar(@RequestParam("usuarioLogin") String usuario,
                           @RequestParam("claveLogin") String clave,
                           Model model) {
        System.out.println("Registrar!!");
        return "vistas/login";
    }
    
    @PostMapping("/home")
    public String homePage() {
        return "vistas/homepage";
    }
}
