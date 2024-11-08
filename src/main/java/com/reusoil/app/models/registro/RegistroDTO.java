package com.reusoil.app.models.registro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 4, message = "El usuario debe tener al menos 4 caracteres.")
    private String usuario;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String clave;

    // ID de Perfil y Empresa
//    private Long perfilId;
//    private Long empresaId;
}
