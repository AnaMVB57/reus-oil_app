package com.reusoil.app.models.registro;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistroDTO {


        @NotBlank(message = "El documento de identidad es obligatorio")
        private String id;

        @NotBlank(message = "El nombre completo es obligatorio")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre solo debe contener letras y espacios")
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
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        private String clave;

        // Getters y setters


}
