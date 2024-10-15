package com.reusoil.app.repository;

import java.util.List;

import com.reusoil.app.models.usuario.UsuarioEntity;

public interface UsuarioRepositoryIface {
    
    public List<UsuarioEntity> obtenerTodos();
    public void guardar(UsuarioEntity calificacion);
    public UsuarioEntity obtenerPorId(Long id);
    public void eliminarPorId(Long id);
}


