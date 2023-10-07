package com.desafio.tecnico.treina.Api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AutorNaoEncontradoException extends RuntimeException {
    
    public AutorNaoEncontradoException() {
        super("Autor não encontrado");
    }
}
