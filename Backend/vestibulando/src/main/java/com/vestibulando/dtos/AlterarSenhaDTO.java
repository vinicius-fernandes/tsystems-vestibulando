package com.vestibulando.dtos;

public class AlterarSenhaDTO {
    private String novaSenha;
    private String token;

    public AlterarSenhaDTO() {
    }

    public AlterarSenhaDTO(String novaSenha, String token) {
        this.novaSenha = novaSenha;
        this.token = token;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
