package com.reusoil.app.models.persona;

import com.reusoil.app.models.empresa.EmpresaEntity;
import com.reusoil.app.models.registro.RegistroDTO;
import com.reusoil.app.models.usuario.UsuarioEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity(name = "persona")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco.")
    @Column(length = 60, nullable = false, unique = true)
    private String nombre;

    @NotBlank(message = "El nombre no puede estar en blanco.")
    private String correo;

    @NotBlank(message = "El nombre no puede estar en blanco.")
    private String telefono;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa_id")
    private EmpresaEntity empresa;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioEntity usuario;

    private boolean estado;

    public static PersonaEntity from(RegistroDTO registroDTO){
        PersonaEntity persona = new PersonaEntity();
        persona.setId(registroDTO.getId());
        persona.setNombre(registroDTO.getNombre());
        persona.setCorreo(registroDTO.getCorreo());
        persona.setTelefono(registroDTO.getTelefono());
        persona.setEmpresa(null); //tener cuidado con esta parte, por si acaso
//        persona.setUsuario(registroDTO.getUsuario()); // Usuario es String en RegistroDTO
        persona.setEstado(true);
        return persona;
    }
}
