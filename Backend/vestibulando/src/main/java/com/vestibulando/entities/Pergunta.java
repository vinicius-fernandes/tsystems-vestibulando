package com.vestibulando.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vestibulando.constraints.perguntas.TotalRespostasConstraint;
import com.vestibulando.constraints.perguntas.TotalRespostasCorretasConstraint;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length=3500)
    @Size(min=4,max=3500,message = "O corpo da pergunta deve possuir de 4 a 3500 caracteres")
    private String corpo;
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;
    @ManyToOne
    @JoinColumn(name = "banca_id")
    private Banca banca;
    @JsonIgnore
    @ManyToMany(mappedBy = "perguntas")
    private Set<Simulado> simulado;

    @OneToMany(
            mappedBy = "pergunta",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    @TotalRespostasConstraint
    @TotalRespostasCorretasConstraint
    private Set<Resposta> respostas = new LinkedHashSet<>();

    public Banca getBanca() {
        return banca;
    }

    public void setBanca(Banca banca) {
        this.banca = banca;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Set<Simulado> getSimulado() {
        return simulado;
    }

    public void setSimulado(Set<Simulado> simulado) {
        this.simulado = simulado;
    }

    @JsonManagedReference
    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }




}
