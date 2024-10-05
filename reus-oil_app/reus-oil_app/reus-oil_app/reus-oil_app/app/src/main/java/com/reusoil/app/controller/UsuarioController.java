package com.reusoil.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reusoil.app.models.Usuario;
import com.reusoil.app.services.UsuarioServiceIface;
import com.reusoil.app.services.UsuarioServiceSQL;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServiceSQL usuarioService;

    public UsuarioController(UsuarioServiceSQL usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
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
