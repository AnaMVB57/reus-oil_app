package com.reusoil.app.models.registro;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegistroDTO {

        @NotNull(message = "El documento de identidad es obligatorio")
        private Long id;

        @NotBlank(message = "El nombre completo es obligatorio")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ ]*$", message = "Ingrese un nombre válido.")
        private String nombre;

        @NotBlank(message = "El usuario es obligatorio")
        @Size(min = 4, max = 20, message = "El usuario debe tener entre 4 y 20 caracteres")
        private String usuario;

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo debe tener un formato válido")
        private String correo;

        @NotBlank(message = "El teléfono es obligatorio")
        private String telefono;

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        private String clave;

        // Getters y setters


}
