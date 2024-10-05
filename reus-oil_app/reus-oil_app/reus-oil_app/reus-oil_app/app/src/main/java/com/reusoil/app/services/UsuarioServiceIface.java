package com.reusoil.app.services;

import java.util.List;

import com.reusoil.app.models.Usuario;

public interface UsuarioServiceIface {
    
    public List<Usuario> obtenerUsuariosTodos(Usuario usuario);
    public Usuario obtenerUsuarioPorId(Long id);
    public void eliminarUsuarioPorId(Long id);
    public void guardarUsuario(Usuario usuario);

}