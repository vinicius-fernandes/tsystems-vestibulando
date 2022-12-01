package com.vestibulando.excepitions;

public class ArgumentoDuplicado extends RuntimeException{

    public ArgumentoDuplicado(String mensagem){
        super(mensagem);
    }
}
