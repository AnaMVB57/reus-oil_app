package com.reusoil.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.reusoil.app.models.usuario.UsuarioEntity;

import jakarta.annotation.PostConstruct;

@Service
public class UsuarioService {

    private List<UsuarioEntity> usuarioEntities = new ArrayList<>();

    public UsuarioEntity registrarUsuario(UsuarioEntity usuarioEntity) {
        usuarioEntities.add(usuarioEntity);
        return usuarioEntity;
    }

    public Optional<UsuarioEntity> obtenerUsuarioPorId(Long id) {
        return usuarioEntities.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public UsuarioEntity actualizarUsuario(UsuarioEntity usuarioEntityActualizado) {
        Optional<UsuarioEntity> usuarioExistente = obtenerUsuarioPorId(usuarioEntityActualizado.getId());
        if (usuarioExistente.isPresent()) {
            UsuarioEntity usuarioEntity = usuarioExistente.get();
            usuarioEntity.setUsuario(usuarioEntityActualizado.getUsuario());
            usuarioEntity.setClave(usuarioEntityActualizado.getClave());
            return usuarioEntity;
        }
        return null;
    }

    // public void eliminarUsuario(Long id) {
    //     usuarios.removeIf(u -> u.getId().equals(id));
    // }

    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
        return usuarioEntities;
    }

//    @PostConstruct
//    public void init() {
//        usuarioEntities.add(new UsuarioEntity(1L, "admin", List.of(), "1234"));
//        usuarioEntities.add(new UsuarioEntity(2L, "empresa", List.of(), "abcd"));
//    }

}
