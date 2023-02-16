package com.example.prueba2.service.impl;

import com.example.prueba2.dao.IUsuarioDao;
import com.example.prueba2.entitie.Usuario;
import com.example.prueba2.mapper.IUsuarioMapper;
import com.example.prueba2.model.UsuarioModel;
import com.example.prueba2.service.IUsuarioService;
import com.example.prueba2.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.LinkLoopException;
import java.util.LinkedList;
import java.util.List;

import static com.example.prueba2.util.Constantes.MENSAJE_ERROR;

@Service
public class UsuarioService implements IUsuarioService {

    private final IUsuarioMapper usuarioMapper;
    private final IUsuarioDao usuarioDao;

    @Autowired
    public UsuarioService(IUsuarioMapper usuarioMapper, IUsuarioDao usuarioDao) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioDao = usuarioDao;
    }

    @Override
    public UsuarioModel crearUsuario(UsuarioModel usuarioModel) {
        Usuario usuario = usuarioMapper.mapFromDTO(usuarioModel);
        return usuarioMapper.mapFromEntity(usuarioDao.save(usuario));
    }

    @Override
    public UsuarioModel modificarUsuario(UsuarioModel usuarioModel) {
        usuarioDao.findById(usuarioModel.getIdUsuario()).orElseThrow(() -> new RuntimeException(Constantes.MENSAJE_NULO));
        Usuario usuario = usuarioMapper.mapFromDTO(usuarioModel);
        return usuarioMapper.mapFromEntity(usuarioDao.save(usuario));
    }

    @Override
    public UsuarioModel eliminarUsuario(int idUsuario) {
        usuarioDao.findById(idUsuario).orElseThrow(() -> new RuntimeException(Constantes.MENSAJE_NULO));
        Usuario usuario = usuarioDao.getById(idUsuario);
        usuarioDao.deleteById(idUsuario);
        return usuarioMapper.mapFromEntity(usuario);
    }

    @Override
    public List<UsuarioModel> listarUsuario(String campo, String valor) {
        List<UsuarioModel> usuarioModelList = new LinkedList<>();
        List<Usuario> usuario;

        if (campo.equals("*") && valor.equals("*")) {
            usuario = usuarioDao.findAll();
        } else {
            usuario = usuarioDao.findByNombreOrApellidoOrCorreoElectronico(valor);
        }

        if (usuario.isEmpty()) {
            UsuarioModel usuarioModel = new UsuarioModel();
            usuarioModel.setMsjError(MENSAJE_ERROR);
            usuarioModelList.add(usuarioModel);
        } else {
            usuarioModelList = usuarioMapper.mapListUsuario(usuario);
        }

        return usuarioModelList;
    }
}

