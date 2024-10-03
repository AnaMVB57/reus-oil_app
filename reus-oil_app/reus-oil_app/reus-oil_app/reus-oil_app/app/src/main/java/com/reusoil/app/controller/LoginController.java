package com.reusoil.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final String usuarioValido = "usuario123";
    private final String claveValida = "clave123";

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "vistas/login"; // Nombre de la vista de login
    }

    // PostMapping que reciba la contraseña y redirija a la vista homepage.html
    @PostMapping("/login")
    public String iniciarSesion(@RequestParam("usuarioLogin") String usuario,
                                @RequestParam("claveLogin") String clave,
                                Model model) {
        if (usuario.isEmpty() || clave.isEmpty()) {
            model.addAttribute("error", "Todos los campos son obligatorios.");
            return "vistas/login";
        }
    
        if (!usuario.equals(usuarioValido) || !clave.equals(claveValida)) {
            model.addAttribute("error", "Usuario o contraseña incorrectos.");
            return "vistas/login";
        }
    
        return "vistas/homepage";
    }
    
    @PostMapping("/home")
    public String homePage() {
        return "vistas/homepage";
    }
}
