package com.reusoil.app.controller.login;

import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.services.usuario.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;

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
