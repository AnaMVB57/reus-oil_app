package com.reusoil.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.reusoil.app.models.Usuario;

import jakarta.annotation.PostConstruct;

@Service
public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public Usuario actualizarUsuario(Usuario usuarioActualizado) {
        Optional<Usuario> usuarioExistente = obtenerUsuarioPorId(usuarioActualizado.getId());
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setUsuario(usuarioActualizado.getUsuario());
            usuario.setCorreo(usuarioActualizado.getCorreo());
            usuario.setClave(usuarioActualizado.getClave());
            return usuario;
        }
        return null;
    }

    // public void eliminarUsuario(Long id) {
    //     usuarios.removeIf(u -> u.getId().equals(id));
    // }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarios;
    }

    @PostConstruct
    public void init() {
        usuarios.add(new Usuario(1L, "admin", "admin@correo.com", "1234"));
        usuarios.add(new Usuario(2L, "empresa", "empresa@corporativo.algo.com", "abcd"));
    }

}
