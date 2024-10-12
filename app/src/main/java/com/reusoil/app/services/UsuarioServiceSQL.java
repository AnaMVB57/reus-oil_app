package com.reusoil.app.services;

import com.reusoil.app.models.Usuario;
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
    public List<Usuario> obtenerUsuariosTodos(Usuario usuario) {
       return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarUsuarioPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // public Optional<Usuario> obtenerUsuarioPorId(Long id) {
    //     return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    // }

    public Usuario actualizarUsuario(Usuario usuarioActualizado) {
         Optional<Usuario> usuarioExistente = obtenerUsuarioPorId(usuarioActualizado.getId());
         if (usuarioExistente.isPresent()) {
             Usuario usuario = usuarioExistente.get();
             usuario.setUsuario(usuarioActualizado.getUsuario());
             usuario.setClave(usuarioActualizado.getClave());
             return usuario;
         }
         return null;
    }

    // public void eliminarUsuario(Long id) {
    //     usuarios.removeIf(u -> u.getId().equals(id));
    // }

    public List<Usuario> obtenerTodosLosUsuarios() {
         return usuarios;
    }


}
