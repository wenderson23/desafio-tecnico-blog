package com.desafio.tecnico.treina.Api.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.tecnico.treina.Api.model.Usuario;
import com.desafio.tecnico.treina.Api.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public Usuario cadastarUsuario(@Valid @RequestBody Usuario idUsuario){
        return usuarioRepository.save(idUsuario);
    }

    @GetMapping
    public List<Usuario> listarAutor(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> obterUsuarioPeloId(@PathVariable("id") Long id) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioExistente);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuarioObj = usuarioExistente.get();

            usuarioObj.setNome(usuario.getNome());
            usuarioObj.setSobrenome(usuario.getSobrenome());
            usuarioObj.setEmail(usuario.getEmail());
            usuarioObj.setSenha(usuario.getSenha());

            return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarioObj));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuarioPeloId(@PathVariable("id") Long id) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        usuarioRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
