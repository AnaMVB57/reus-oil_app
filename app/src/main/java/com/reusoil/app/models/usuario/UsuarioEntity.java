package com.reusoil.app.models.usuario;

import com.reusoil.app.models.perfil.PerfilEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity(name = "usuario")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotBlank
    @Column(length = 60, nullable = false)
    private String usuario;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "usuario_perfil",
//    joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name = "perfil_id", referencedColumnName = "id"))
//    private Collection<PerfilEntity> perfilEntity;

    @NotEmpty
    @NotBlank
    @Column(nullable = false)
    private String clave;

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

//    public Collection<PerfilEntity> getPerfilEntity() {
//        return perfilEntity;
//    }

//    public void setPerfilEntity(Collection<PerfilEntity> perfilEntity) {
//        this.perfilEntity = perfilEntity;
//    }

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
                ", clave='" + clave + '\'' +
                '}';
    }
}
