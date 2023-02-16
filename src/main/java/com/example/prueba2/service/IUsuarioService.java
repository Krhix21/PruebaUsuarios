package com.example.prueba2.service;

import com.example.prueba2.model.UsuarioModel;

import java.util.List;

public interface  IUsuarioService {

    UsuarioModel crearUsuario(UsuarioModel usuarioModel);

    UsuarioModel modificarUsuario(UsuarioModel usuarioModel);

    UsuarioModel eliminarUsuario(int idUsuario);

    List<UsuarioModel> listarUsuario(String campo, String valor);
}
