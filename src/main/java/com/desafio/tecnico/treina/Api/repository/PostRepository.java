package com.desafio.tecnico.treina.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.tecnico.treina.Api.model.Post;

public interface PostRepository extends JpaRepository <Post, Long> {
    
}
