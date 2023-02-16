package com.example.prueba2.controller;

import com.example.prueba2.model.UsuarioModel;
import com.example.prueba2.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    @CrossOrigin
    public ResponseEntity<UsuarioModel> crearUsuario(@RequestBody UsuarioModel usuarioModel) {
        return new ResponseEntity(usuarioService.crearUsuario(usuarioModel), HttpStatus.CREATED);
    }

    @GetMapping("/listar/{campo}/{valor}")
    public ResponseEntity<UsuarioModel> listarUsuario(@PathVariable("campo") String campo, @PathVariable("valor") String valor){
        return new ResponseEntity(usuarioService.listarUsuario(campo, valor), HttpStatus.OK);
    }

    @PutMapping("/modificar")
    public ResponseEntity<UsuarioModel> modificarUsuario(@RequestBody UsuarioModel usuarioModel) {
        return new ResponseEntity(usuarioService.modificarUsuario(usuarioModel), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<UsuarioModel> eliminarUsuario(@PathVariable ("idUsuario") int idUsuario) {
        return new ResponseEntity(usuarioService.eliminarUsuario(idUsuario), HttpStatus.OK);
    }
}