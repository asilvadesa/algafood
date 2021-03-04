package com.algaworks.algafood.domain.exception;

public class EntindadeNaoEncontradaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntindadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }

}
