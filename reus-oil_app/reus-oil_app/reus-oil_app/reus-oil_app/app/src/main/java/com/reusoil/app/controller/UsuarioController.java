package com.reusoil.app.controller;

import com.reusoil.app.models.Usuario;
import com.reusoil.app.models.UsuarioApi;
import com.reusoil.app.services.UsuarioServiceSQL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServiceSQL usuarioService;

    public UsuarioController(UsuarioServiceSQL usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public Usuario registrarUsuario(@ModelAttribute UsuarioApi usuario) {
        return usuarioService.registrarUsuario(
                Usuario.builder()
                        .usuario(usuario.getUsuario())
                        .clave(usuario.getClave())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id).orElse(null);
    }

    @PutMapping("/actualizar")
    public Usuario actualizarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(usuario);
    }

    // @DeleteMapping("/eliminar/{id}")
    // public void eliminarUsuario(@PathVariable Long id) {
    //     usuarioService.eliminarUsuario(id);
    // }

    @GetMapping("/todos")
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }
}
