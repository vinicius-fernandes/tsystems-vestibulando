package com.vestibulando.dtos;

import com.vestibulando.entities.Materia;

public class MateriaDTO {
    private long id;
    private String nome;

    public MateriaDTO(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public MateriaDTO(Materia mat){
        this.id=mat.getId();
        this.nome=mat.getNome();
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
}
