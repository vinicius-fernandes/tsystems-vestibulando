package com.vestibulando.entities;

import javax.persistence.*;
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
    @ManyToMany(mappedBy = "perguntas")
    private List<Simulado> simulado;

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
}
