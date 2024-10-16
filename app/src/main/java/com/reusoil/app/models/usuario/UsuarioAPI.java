package com.reusoil.app.models.usuario;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UsuarioAPI {

    @Getter
    @Setter
    private String usuario;
    private String clave;

}
