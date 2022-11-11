package com.vestibulando.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.Fetch;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String corpo;
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;
    @ManyToOne
    @JoinColumn(name = "banca_id")
    private Banca banca;
    @JsonIgnore
    @ManyToMany(mappedBy = "perguntas")
    private List<Simulado> simulado;

    @OneToMany(
            mappedBy = "pergunta",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Resposta> respostas = new ArrayList<>();

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

    public List<Simulado> getSimulado() {
        return simulado;
    }

    public void setSimulado(List<Simulado> simulado) {
        this.simulado = simulado;
    }

    @JsonManagedReference
    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

}
