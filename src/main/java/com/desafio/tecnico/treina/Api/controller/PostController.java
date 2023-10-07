package com.desafio.tecnico.treina.Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.tecnico.treina.Api.dto.PostDto;
import com.desafio.tecnico.treina.Api.exception.AutorNaoEncontradoException;
import com.desafio.tecnico.treina.Api.model.Comentario;
import com.desafio.tecnico.treina.Api.model.Post;
import com.desafio.tecnico.treina.Api.model.Usuario;
import com.desafio.tecnico.treina.Api.repository.ComentarioRepository;
import com.desafio.tecnico.treina.Api.repository.PostRepository;
import com.desafio.tecnico.treina.Api.repository.UsuarioRepository;


import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.Date;


@RestController
@RequestMapping("/posts")
public class PostController {

    @GetMapping
    public List<Post> listarPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> buscarPostPorId(@PathVariable("id") Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Post> criarPost(@Valid @RequestBody PostDto postDTO) {
        Optional<Usuario> authorOptional = usuarioRepository.findById(postDTO.getAuthorId());
        if (authorOptional.isPresent()) {
            Usuario author = authorOptional.get();
            Post post = new Post();
            post.setTitulo(postDTO.getTitulo());
            post.setConteudo(postDTO.getConteudo());
            post.setAutor(author);

            // Defina a data de criação como a data atual
            post.setDataDeCriacao(new Date()); // Isso adiciona a data atual

            Post savedPost = postRepository.save(post);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
        } else {
            throw new AutorNaoEncontradoException();
        }
    }

   

    @PutMapping("/{id}")
    public ResponseEntity<Post> atualizarPostPorId(@PathVariable("id") Long id, @Valid @RequestBody PostDto postDTO) {
        Optional<Post> postExistente = postRepository.findById(id);

        if (postExistente.isPresent()) {
            Post post = postExistente.get();

            // Verifique se o autor existe e atualize apenas se o ID do autor estiver
            // presente no DTO.
            if (postDTO.getAuthorId() != null) {
                Optional<Usuario> authorOptional = usuarioRepository.findById(postDTO.getAuthorId());

                if (authorOptional.isPresent()) {
                    Usuario author = authorOptional.get();
                    post.setAutor(author);
                } else {
                    throw new AutorNaoEncontradoException();
                }
            }

            // Atualize os outros campos do Post
            post.setTitulo(postDTO.getTitulo());
            post.setConteudo(postDTO.getConteudo());

            // Salve a atualização do Post
            Post updatedPost = postRepository.save(post);

            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPostPorId(@PathVariable("id") Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();

           
            List<Comentario> comentarios = comentarioRepository.findByPost(post);
            comentarioRepository.deleteAll(comentarios);

            // Exclua o próprio Post
            postRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

}
