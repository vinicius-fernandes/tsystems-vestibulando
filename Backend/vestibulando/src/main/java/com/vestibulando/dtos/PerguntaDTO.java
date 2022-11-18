package com.vestibulando.dtos;

import com.vestibulando.entities.Pergunta;

import java.util.List;

public class PerguntaDTO {

    long idPergunta;
    String corpo;

    List<RespostaDTO> respostas;

    public PerguntaDTO() {
    }

    public PerguntaDTO(long idPergunta, String corpo, List<RespostaDTO> respostas) {
        this.idPergunta = idPergunta;
        this.corpo = corpo;
        this.respostas = respostas;
    }

    public PerguntaDTO(Pergunta pergunta){
        this.setIdPergunta(pergunta.getId());
        this.setCorpo(pergunta.getCorpo());
        this.setRespostas(pergunta.getRespostas().stream().map(RespostaDTO::new).toList());
    }

    public long getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(long idPergunta) {
        this.idPergunta = idPergunta;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public List<RespostaDTO> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostaDTO> respostas) {
        this.respostas = respostas;
    }
}
