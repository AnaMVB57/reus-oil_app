// package com.reusoil.app.repository;

// import java.security.SecureRandom;
// import java.util.List;

// import org.springframework.stereotype.Repository;

// import com.reusoil.app.models.usuario.Usuario;

// @Repository
// public class UsuarioRepository implements UsuarioDAOIface{

//     private List<Usuario> usuarios;
//     private SecureRandom rnd = new SecureRandom();

//     //Revisar
//     public UsuarioRepository(){

//     }

//     @Override
//     public List<Usuario> obtenerTodos() {
//         return usuarios;
//     }

//     @Override
//     public void guardar(Usuario usuario) {
//         if (usuario.getId() == null) {
//             usuario.setId(rnd.nextInt(9000) + 1000L);
//             usuarios.add(usuario);
//         }
//         else {
//             for (int i = 0; i < usuarios.size(); i++) {
//                 Usuario cal = usuarios.get(i);
//                 if (cal.getId().equals(usuario.getId())) {
//                     usuarios.set(i, usuario);
//                     return;
//                 }
//             }
//         }
//     }

//     @Override
//     public Usuario obtenerPorId(Long id) {
//         Usuario usuario = null;
//         for (Usuario us : usuarios) {
//             if (id.equals(us.getId())) {
//                 usuario = us;
//                 break;
//             }
//         }
//         return usuario;     
//     }

//     @Override
//     public void eliminarPorId(Long id) {
//         for (int i = 0; i < usuarios.size(); i++) {
//             Usuario us = usuarios.get(i);
//             if (us.getId().equals(id)) {
//                 usuarios.remove(i);
//                 return;
//             }
//         }
//     }
    
// }
