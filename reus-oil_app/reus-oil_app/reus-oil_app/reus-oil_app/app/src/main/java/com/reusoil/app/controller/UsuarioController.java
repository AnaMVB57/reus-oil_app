package com.reusoil.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.reusoil.app.models.Usuario;
import com.reusoil.app.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

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
