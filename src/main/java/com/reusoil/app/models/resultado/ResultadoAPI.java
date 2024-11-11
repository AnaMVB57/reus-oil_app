package com.reusoil.app.models.resultado;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ResultadoAPI {

    @NotNull
    private float resultadoTemperatura;

    @NotNull
    private float nivelLlenado;

    @Column(name = "fecha_medicion", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaMedicion = LocalDate.now();
}
