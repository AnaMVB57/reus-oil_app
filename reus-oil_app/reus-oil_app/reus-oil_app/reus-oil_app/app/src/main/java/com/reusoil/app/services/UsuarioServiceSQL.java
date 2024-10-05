package com.reusoil.app.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reusoil.app.models.Usuario;
import com.reusoil.app.repository.UsuarioDAOIface;

@Service
public class UsuarioServiceSQL implements UsuarioServiceIface {

    final private UsuarioDAOIface usuarioDAO;

    public UsuarioServiceSQL(UsuarioDAOIface usuarioDAOIface){
        this.usuarioDAO = usuarioDAOIface;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerUsuariosTodos(Usuario usuario) {
       return usuarioDAO.findAll();
    }

    @Override
    @Transactional
    public void eliminarUsuarioPorId(Long id) {
       usuarioDAO.deleteById(id);
    }

    @Override
    @Transactional
    public void guardarUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    // private List<Usuario> usuarios = new ArrayList<>();

    // public Usuario registrarUsuario(Usuario usuario) {
    //     usuarios.add(usuario);
    //     return usuario;
    // }

    // public Optional<Usuario> obtenerUsuarioPorId(Long id) {
    //     return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    // }

    // public Usuario actualizarUsuario(Usuario usuarioActualizado) {
    //     Optional<Usuario> usuarioExistente = obtenerUsuarioPorId(usuarioActualizado.getId());
    //     if (usuarioExistente.isPresent()) {
    //         Usuario usuario = usuarioExistente.get();
    //         usuario.setUsuario(usuarioActualizado.getUsuario());
    //         usuario.setClave(usuarioActualizado.getClave());
    //         return usuario;
    //     }
    //     return null;
    // }

    // public void eliminarUsuario(Long id) {
    //     usuarios.removeIf(u -> u.getId().equals(id));
    // }

    // public List<Usuario> obtenerTodosLosUsuarios() {
    //     return usuarios;
    // }


}
