package com.vestibulando.dtos;

import com.vestibulando.entities.Pergunta;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PerguntaDTO {

    long idPergunta;
    String corpo;

    Set<RespostaDTO> respostas;

    BancaDTO banca;
    MateriaDTO materia;

    public PerguntaDTO() {
    }

    public PerguntaDTO(long idPergunta, String corpo, Set<RespostaDTO> respostas) {
        this.idPergunta = idPergunta;
        this.corpo = corpo;
        this.respostas = respostas;
    }

    public PerguntaDTO(Pergunta pergunta){
        this.setIdPergunta(pergunta.getId());
        this.setCorpo(pergunta.getCorpo());
        this.setRespostas(pergunta.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toSet()));
        this.setBanca(new BancaDTO(pergunta.getBanca()));
        this.setMateria(new MateriaDTO(pergunta.getMateria()));
    }

    public BancaDTO getBanca() {
        return banca;
    }

    public void setBanca(BancaDTO banca) {
        this.banca = banca;
    }

    public MateriaDTO getMateria() {
        return materia;
    }

    public void setMateria(MateriaDTO materia) {
        this.materia = materia;
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

    public Set<RespostaDTO> getRespostas() {
        return respostas;
    }

    public void setRespostas(Set<RespostaDTO> respostas) {
        this.respostas = respostas;
    }
}
