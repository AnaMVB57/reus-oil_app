package com.reusoil.app.services;

import java.util.List;
import java.util.Optional;

import com.reusoil.app.models.Usuario;

public interface UsuarioServiceIface {
    
    List<Usuario> obtenerUsuariosTodos(Usuario usuario);
    Optional<Usuario> obtenerUsuarioPorId(Long id);
    void eliminarUsuarioPorId(Long id);
    void guardarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorUsuario(String usuario);
}