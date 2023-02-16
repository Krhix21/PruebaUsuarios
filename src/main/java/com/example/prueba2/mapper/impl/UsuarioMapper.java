package com.example.prueba2.mapper.impl;

import com.example.prueba2.entitie.Usuario;
import com.example.prueba2.mapper.IUsuarioMapper;
import com.example.prueba2.model.UsuarioModel;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

@Component
public class UsuarioMapper implements IUsuarioMapper {

    @Override
    public UsuarioModel mapFromEntity(Usuario usuario){
        UsuarioModel usuarioModel = new UsuarioModel();

        usuarioModel.setIdUsuario(usuario.getIdUsuario());
        usuarioModel.setNombre(usuario.getNombre());
        usuarioModel.setApellido(usuario.getApellido());
        usuarioModel.setCorreoElectronico(usuario.getCorreoElectronico());
        try {
            usuarioModel.setContrasena(enmascaraContrasena(usuario.getContrasena()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return usuarioModel;
    }

    @Override
    public  Usuario mapFromDTO(UsuarioModel usuarioModel){
        Usuario usuario = new Usuario();

        usuario.setIdUsuario(usuarioModel.getIdUsuario());
        usuario.setNombre(usuarioModel.getNombre());
        usuario.setApellido(usuarioModel.getApellido());
        usuario.setCorreoElectronico(usuarioModel.getCorreoElectronico());
        usuario.setContrasena(usuarioModel.getContrasena());

        return usuario;
    }

    @Override
    public List<UsuarioModel> mapListUsuario(List<Usuario> listUsuario) {
        List<UsuarioModel> usuarioModelList = new LinkedList<>();
        for (Usuario usuario: listUsuario) {
            usuarioModelList.add(mapFromEntity(usuario));
        }
        return usuarioModelList;
    }

    private String enmascaraContrasena(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(contrasena.getBytes());
        String maskedPassword = new String(hash, StandardCharsets.UTF_8);
        return maskedPassword;
    }

}
