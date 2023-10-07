package com.desafio.tecnico.treina.Api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.tecnico.treina.Api.model.Post;
import com.desafio.tecnico.treina.Api.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping
    public @ResponseBody Post cadastrarPost(@RequestBody Post post){
        return postRepository.save(post);
    }

    @GetMapping
    public List<Post> listarPost(){
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> obterPostPeloId(@PathVariable("id") Long id) {
        Optional<Post> existePostDesteId = postRepository.findById(id);

        if (existePostDesteId.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(existePostDesteId);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> atualizarPostagem(@PathVariable("id") Long id, @RequestBody Post post) {
        Optional<Post> existePostDesteId = postRepository.findById(id);

        if (existePostDesteId.isPresent()) {
            Post postObj = existePostDesteId.get();

            postObj.setTitulo(post.getTitulo());
            postObj.setConteudo(post.getConteudo());

            return ResponseEntity.status(HttpStatus.OK).body(postRepository.save(postObj));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPostPeloId(@PathVariable("id") Long id) {
        Optional<Post> existePostDesteId = postRepository.findById(id);

        if (existePostDesteId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        postRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
