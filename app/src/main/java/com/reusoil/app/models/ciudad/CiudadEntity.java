package com.reusoil.app.models.ciudad;

import com.reusoil.app.models.empresa.EmpresaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "ciudad")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CiudadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private boolean estado;

    @OneToMany(mappedBy = "ciudad")
    private List<EmpresaEntity> empresas;

}
