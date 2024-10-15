package com.reusoil.app.services;

import java.util.List;
import java.util.Optional;

import com.reusoil.app.models.usuario.UsuarioEntity;

public interface UsuarioServiceIface {
    
    List<UsuarioEntity> obtenerUsuariosTodos(UsuarioEntity usuarioEntity);
    Optional<UsuarioEntity> obtenerUsuarioPorId(Long id);
    void eliminarUsuarioPorId(Long id);
    void guardarUsuario(UsuarioEntity usuarioEntity);
    UsuarioEntity obtenerUsuarioPorUsuario(String usuario);
}