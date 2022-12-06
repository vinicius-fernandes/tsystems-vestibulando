package com.vestibulando.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vestibulando.entities.Pergunta;
import com.vestibulando.entities.Resposta;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RespostaDTO {
    private Long id;
    @NotBlank(message = "Informe descrição")
    @Size(message = "O campo deve ter entre 3 e 10 caracteres", max = 2500, min = 3)
    private String descricao;
    private Pergunta pergunta;

    public RespostaDTO() {
    }
    public RespostaDTO(Resposta resposta) {
        id = resposta.getId();
        descricao = resposta.getDescricao();
        pergunta = resposta.getPergunta();
    }

    @JsonBackReference
    public Pergunta getPergunta() {
        return pergunta;
    }
    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
