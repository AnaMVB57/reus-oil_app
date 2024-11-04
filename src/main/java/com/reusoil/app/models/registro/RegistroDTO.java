package com.reusoil.app.models.registro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistroDTO {

    // Campos de Persona
    @NotNull(message = "El campo no puede estar vacío.")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombre;

    @NotBlank(message = "El correo no puede estar vacío.")
    private String correo;

    @NotBlank(message = "El teléfono no puede estar vacío.")
    private String telefono;

    // Campos de Usuario
    @NotBlank(message = "El usuario no puede estar vacío.")
    private String usuario;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    private String clave;

    // ID de Perfil y Empresa
//    private Long perfilId;
//    private Long empresaId;
}
