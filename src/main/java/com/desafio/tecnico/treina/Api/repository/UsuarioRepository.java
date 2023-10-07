package com.desafio.tecnico.treina.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.tecnico.treina.Api.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    
}
