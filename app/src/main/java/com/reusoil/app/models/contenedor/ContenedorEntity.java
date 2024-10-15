package com.reusoil.app.models.contenedor;

import com.reusoil.app.models.empresa.EmpresaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "contenedor")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContenedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float capacidad;

    @Column(nullable = false)
    private String ubicacion;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaInst;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", referencedColumnName = "id", nullable = false)
    private EmpresaEntity empresa;

    @Column(nullable = false)
    private boolean estado;

    // Getters, setters, y toString
}
