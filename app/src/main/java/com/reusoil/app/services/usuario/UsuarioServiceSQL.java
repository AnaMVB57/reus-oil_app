package com.reusoil.app.services.usuario;

import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceSQL implements UsuarioServiceIface {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceSQL(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioEntity> obtenerUsuariosTodos(UsuarioEntity usuarioEntity) {
       return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarUsuarioPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void guardarUsuario(UsuarioEntity usuarioEntity) {
        usuarioRepository.save(usuarioEntity);
    }

    @Override
    public UsuarioEntity obtenerUsuarioPorUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioEntity> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    private List<UsuarioEntity> usuarioEntities = new ArrayList<>();

    public UsuarioEntity registrarUsuario(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    // public Optional<Usuario> obtenerUsuarioPorId(Long id) {
    //     return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    // }

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


}
