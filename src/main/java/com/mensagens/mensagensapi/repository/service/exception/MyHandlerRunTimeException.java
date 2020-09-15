package com.mensagens.mensagensapi.repository.service.exception;

public class MyHandlerRunTimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MyHandlerRunTimeException(String msg) {
        super(msg);
    }
}