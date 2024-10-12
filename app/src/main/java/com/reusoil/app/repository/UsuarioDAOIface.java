package com.reusoil.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reusoil.app.models.Usuario;

public interface UsuarioDAOIface extends JpaRepository<Usuario, Long>{
    
}