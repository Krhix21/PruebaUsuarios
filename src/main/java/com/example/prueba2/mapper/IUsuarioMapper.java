package com.example.prueba2.mapper;

import com.example.prueba2.entitie.Usuario;
import com.example.prueba2.model.UsuarioModel;

import java.util.List;

public interface IUsuarioMapper {

    UsuarioModel mapFromEntity(Usuario usuario);

    Usuario mapFromDTO(UsuarioModel usuarioModel);

    List<UsuarioModel> mapListUsuario(List<Usuario> listUsuario);
}
