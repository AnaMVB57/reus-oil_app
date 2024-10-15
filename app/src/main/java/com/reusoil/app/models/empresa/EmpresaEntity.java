package com.reusoil.app.models.empresa;

import com.reusoil.app.models.ciudad.CiudadEntity;
import com.reusoil.app.models.tipoempresa.TipoEmpresaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "empresa")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    private String direccion;

    @NotBlank
    @Column(nullable = false)
    private String telefono;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaRegistro;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idCiudad", referencedColumnName = "id", nullable = false)
    private CiudadEntity ciudad;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idTipoEmpresa", referencedColumnName = "id", nullable = false)
    private TipoEmpresaEntity tipoEmpresa;

    @Column(nullable = false)
    private boolean estado;

    // Getters, setters, y toString
}
