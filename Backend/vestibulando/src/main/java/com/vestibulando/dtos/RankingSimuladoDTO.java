package com.vestibulando.dtos;

import org.springframework.beans.factory.annotation.Value;

public class RankingSimuladoDTO {
    private long idUsuario;
    private String emailUsuario;
    private long acertosUsuario;

    public RankingSimuladoDTO() {
    }

    public RankingSimuladoDTO(long idUsuario, String emailUsuario, long acertosUsuario) {
        this.idUsuario = idUsuario;
        this.emailUsuario = emailUsuario;
        this.acertosUsuario = acertosUsuario;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public long getAcertosUsuario() {
        return acertosUsuario;
    }

    public void setAcertosUsuario(long acertosUsuario) {
        this.acertosUsuario = acertosUsuario;
    }
}
