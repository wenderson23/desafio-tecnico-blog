package com.desafio.tecnico.treina.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.tecnico.treina.Api.model.Comentario;

public interface ComentarioRepository extends JpaRepository <Comentario, Long> {
    
}
