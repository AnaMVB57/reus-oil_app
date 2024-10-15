package com.reusoil.app.models.tipoempresa;

import com.reusoil.app.models.empresa.EmpresaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "tipo_empresa")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoEmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipoEmpresa;

    @Column(nullable = false)
    private boolean estado;

    @OneToMany(mappedBy = "tipoEmpresa")
    private List<EmpresaEntity> empresas;

    // Getters, setters, y toString
}
