package com.vestibulando.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Banca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(message = "A sigla precisa possuir no mínimo 2 caracteres e no máximo 50", min = 2, max = 50)
    private String sigla;

    @Column(unique = true)
    @Size(message = "A nome precisa possuir no mínimo 2 caracteres e no máximo 250", min = 2, max = 250)
    @NotBlank(message = "É necessário haver uma nome.")
    @NotNull(message = "A nome não pode ser nula.")
    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "bancas")
    private List<Simulado> simulado;

    @JsonIgnore
    @OneToMany(
            mappedBy = "banca",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Pergunta> perguntas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Simulado> getSimulado() {
        return simulado;
    }

    public void setSimulado(List<Simulado> simulado) {
        this.simulado = simulado;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }
}
