package com.desafio.tecnico.treina.Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.desafio.tecnico.treina.Api.model.Usuario;
import com.desafio.tecnico.treina.Api.repository.UsuarioRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @PostMapping
    public Usuario cadastrarUsuario(@Valid @RequestBody Usuario idUsuario) {
        return usuarioRepository.save(idUsuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletarUsuarioPeloId(@PathVariable("id") Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }


    @PutMapping("/{id}")
    public Usuario atualizarUsuarioPorId(@Valid @PathVariable("id") Long idUsuario, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);
        if (usuarioExistente.isPresent()) {
            Usuario usuarioObj = usuarioExistente.get();

            usuarioObj.setNome(usuario.getNome());
            usuarioObj.setSobrenome(usuario.getSobrenome());
            usuarioObj.setEmail(usuario.getEmail());
            

            return usuarioRepository.save(usuarioObj);
        }
        return null;

    }

    @Autowired
    private UsuarioRepository usuarioRepository;
}
