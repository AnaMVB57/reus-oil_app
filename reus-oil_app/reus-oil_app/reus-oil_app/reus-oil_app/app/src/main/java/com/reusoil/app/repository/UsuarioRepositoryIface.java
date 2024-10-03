package com.reusoil.app.repository;

import java.util.List;

import com.reusoil.app.models.Usuario;

public interface UsuarioRepositoryIface {
    
    public List<Usuario> obtenerTodos();
    public void guardar(Usuario calificacion);
    public Usuario obtenerPorId(Long id);
    public void eliminarPorId(Long id);
}


