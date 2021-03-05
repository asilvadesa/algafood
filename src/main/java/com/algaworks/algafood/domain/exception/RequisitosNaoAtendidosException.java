package com.algaworks.algafood.domain.exception;

public class RequisitosNaoAtendidosException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RequisitosNaoAtendidosException(String message) {
        super(message);
    }
}
