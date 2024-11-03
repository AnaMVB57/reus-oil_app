package com.reusoil.app.repository.persona;

import com.reusoil.app.models.persona.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

    Optional<PersonaEntity> findByNombre(String nombre);
    List<PersonaEntity> findByEstado(boolean estado);
    Optional<PersonaEntity> findByUsuarioId(Long id);
}
