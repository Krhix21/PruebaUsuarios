package com.example.prueba2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioModel {

    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String contrasena;
    private String msjError;

}
