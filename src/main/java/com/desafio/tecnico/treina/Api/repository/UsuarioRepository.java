package com.desafio.tecnico.treina.Api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.desafio.tecnico.treina.Api.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
        Optional<Usuario> findByEmail(String email);

        Optional<List<Usuario>> findByNome(String nome);

}
