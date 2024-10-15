package com.reusoil.app.models.recoleccion;

import com.reusoil.app.models.empresa.EmpresaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "recoleccion")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecoleccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaRecoleccion;

    @Column(nullable = false)
    private float volumenRecogido;

    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id", nullable = false)
    private EmpresaEntity empresa;

    @Column(nullable = false)
    private boolean estado;

}
