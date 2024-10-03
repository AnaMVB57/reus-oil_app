package com.reusoil.app.models;

public class Usuario {
    private Long id;
    private String usuario;
    private String correo;
    // private int idPerfil;
    private String clave;

    public Usuario() {
    }

    public Usuario(Long id, String usuario, String correo, String clave) {
        this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.clave = clave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", correo='" + correo + '\'' +
                ", clave='" + clave + '\'' +
                '}';
    }
}
