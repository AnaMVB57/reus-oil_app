package com.reusoil.app.models.usuario;

import com.reusoil.app.models.perfil.PerfilEntity;
import com.reusoil.app.models.persona.PersonaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity(name = "usuario")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo no puede estar vacío.")
    @Column(length = 60, nullable = false, unique = true)
    private String usuario;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "perfil_id")
    private PerfilEntity perfil;

    @NotBlank(message = "El campo no puede estar vacío.")
    @Column(nullable = false)
    private String clave;

    @OneToOne(mappedBy = "usuario")
    private PersonaEntity persona;

    private boolean estado;


}
