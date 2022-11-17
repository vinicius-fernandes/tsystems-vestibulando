package com.vestibulando.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
public class Simulado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "simulado_materia",
            joinColumns = @JoinColumn(name = "simulado_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id"))
    private Set<Materia> materias;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "simulado_banca",
            joinColumns = @JoinColumn(name = "simulado_id"),
            inverseJoinColumns = @JoinColumn(name = "banca_id"))
    private Set<Banca> bancas;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "simulado_pergunta",
            joinColumns = @JoinColumn(name = "simulado_id"),
            inverseJoinColumns = @JoinColumn(name = "pergunta_id"))
    private Set<Pergunta> perguntas;

    private Instant createdAt;

    private Instant updatedAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = Instant.now();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(Set<Materia> materias) {
        this.materias = materias;
    }

    public Set<Banca> getBancas() {
        return bancas;
    }

    public void setBancas(Set<Banca> bancas) {
        this.bancas = bancas;
    }

    public Set<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(Set<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }
}
