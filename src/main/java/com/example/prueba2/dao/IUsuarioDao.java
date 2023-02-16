package com.example.prueba2.dao;

import com.example.prueba2.entitie.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT usuario FROM Usuario usuario WHERE usuario.nombre = :valor OR usuario.apellido = :valor OR usuario.correoElectronico = :valor", nativeQuery = false)
    List<Usuario> findByNombreOrApellidoOrCorreoElectronico(@Param("valor") String valor);

}
