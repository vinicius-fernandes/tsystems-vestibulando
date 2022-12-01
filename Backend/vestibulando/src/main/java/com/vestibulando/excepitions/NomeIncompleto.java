package com.vestibulando.excepitions;

import org.springframework.web.bind.MethodArgumentNotValidException;

public class NomeIncompleto extends RuntimeException {

    public NomeIncompleto (String mensagem){
        super(mensagem);
    }
}
