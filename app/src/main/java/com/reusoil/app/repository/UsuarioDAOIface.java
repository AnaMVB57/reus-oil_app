package com.reusoil.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reusoil.app.models.usuario.UsuarioEntity;

public interface UsuarioDAOIface extends JpaRepository<UsuarioEntity, Long>{
    
}