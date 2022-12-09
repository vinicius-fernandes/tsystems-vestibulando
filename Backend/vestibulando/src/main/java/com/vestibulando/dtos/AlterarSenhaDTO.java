package com.vestibulando.dtos;

import java.util.Objects;

public class AlterarSenhaDTO {
    private String novaSenha;

    private String confirmarNovaSenha;
    private String token;

    public AlterarSenhaDTO() {
    }

    public boolean senhasCoincidem(){
        return Objects.equals(this.novaSenha, this.confirmarNovaSenha);
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

    public AlterarSenhaDTO(String novaSenha, String confirmarNovaSenha, String token) {
        this.novaSenha = novaSenha;
        this.confirmarNovaSenha = confirmarNovaSenha;
        this.token = token;
    }


    public String getConfirmarNovaSenha() {
        return confirmarNovaSenha;
    }

    public void setConfirmarNovaSenha(String confirmarNovaSenha) {
        this.confirmarNovaSenha = confirmarNovaSenha;
    }
}
