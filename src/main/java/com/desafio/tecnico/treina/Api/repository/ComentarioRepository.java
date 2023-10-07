package com.desafio.tecnico.treina.Api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.tecnico.treina.Api.model.Comentario;
import com.desafio.tecnico.treina.Api.model.Post;

public interface ComentarioRepository extends JpaRepository <Comentario, Long> {

    List<Comentario> findByPost(Post post);
    
}
