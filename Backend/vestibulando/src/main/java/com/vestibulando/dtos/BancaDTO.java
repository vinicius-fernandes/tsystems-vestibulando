package com.vestibulando.dtos;

import com.vestibulando.entities.Banca;

public class BancaDTO {
    private long id;
    private String nome;
    private String sigla;

    public BancaDTO(long id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }
    public BancaDTO(Banca banca){
        this.id=banca.getId();
        this.nome=banca.getNome();
        this.sigla=banca.getSigla();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
