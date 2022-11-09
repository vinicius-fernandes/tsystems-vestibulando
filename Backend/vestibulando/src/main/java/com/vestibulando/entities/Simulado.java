package com.vestibulando.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Simulado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    @JoinTable(
            name = "simulado_materia",
            joinColumns = @JoinColumn(name = "simulado_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id"))
    private List<Materia> materias;
    @ManyToMany
    @JoinTable(
            name = "simulado_banca",
            joinColumns = @JoinColumn(name = "simulado_id"),
            inverseJoinColumns = @JoinColumn(name = "banca_id"))
    private List<Banca> bancas;
    @ManyToMany
    @JoinTable(
            name = "simulado_pergunta",
            joinColumns = @JoinColumn(name = "simulado_id"),
            inverseJoinColumns = @JoinColumn(name = "pergunta_id"))
    private List<Pergunta> perguntas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public List<Banca> getBancas() {
        return bancas;
    }

    public void setBancas(List<Banca> bancas) {
        this.bancas = bancas;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }
}
