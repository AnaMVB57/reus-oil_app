package com.reusoil.app.models.empresa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reusoil.app.models.ciudad.CiudadEntity;
import com.reusoil.app.models.contenedor.ContenedorEntity;
import com.reusoil.app.models.persona.PersonaEntity;
import com.reusoil.app.models.tipo_empresa.TipoEmpresaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity(name = "empresa")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmpresaEntity {

    @Id
    @NotNull(message = "El campo no puede estar en blanco.")
    private Long id;

    @NotBlank(message = "El campo no puede estar en blanco.")
    private String nombre;

    @NotBlank(message = "El campo no puede estar en blanco.")
    private String direccion;

    @NotBlank(message = "El campo no puede estar en blanco.")
    @Pattern(regexp = "^[0-9]+$", message = "Este campo sólo puede contener números.")
    private String telefono;

    @NotBlank(message = "El campo no puede estar en blanco.")
    @Email(message = "Ingrese un correo válido.")
    private String correo;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaRegistro = LocalDate.now();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id", nullable = false)
    private CiudadEntity ciudad;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_tipo_empresa", referencedColumnName = "id", nullable = false)
    private TipoEmpresaEntity tipoEmpresa;

    @ToString.Exclude
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PersonaEntity> persona;

    @ToString.Exclude
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContenedorEntity> contenedor;

    @Column(nullable = false)
    private boolean estado;

}
