package com.reusoil.app.controller;

import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.models.usuario.UsuarioAPI;
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
    public String registrarUsuario(@ModelAttribute UsuarioAPI usuario) {
        usuarioService.registrarUsuario(
                UsuarioEntity.builder()
                        .usuario(usuario.getUsuario())
                        .clave(usuario.getClave())
                        .build()
        );
        return "redirect:/mostrar-login";
    }

    @GetMapping("/{id}")
    public UsuarioEntity obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id).orElse(null);
    }

    @PutMapping("/actualizar")
    public UsuarioEntity actualizarUsuario(@RequestBody UsuarioEntity usuarioEntity) {
        return usuarioService.actualizarUsuario(usuarioEntity);
    }

    // @DeleteMapping("/eliminar/{id}")
    // public void eliminarUsuario(@PathVariable Long id) {
    //     usuarioService.eliminarUsuario(id);
    // }

    @GetMapping("/todos")
    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }
}
