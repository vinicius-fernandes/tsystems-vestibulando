package com.vestibulando.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vestibulando.entities.Banca;
import com.vestibulando.entities.Materia;
import com.vestibulando.entities.Simulado;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SimuladoSimplificadoDTO {

    private Long id;

    private Long qntPerguntas;


    @JsonIgnore
    private String materias;
@JsonIgnore
    private String bancas;

    private Set<String> bancasList;

    private Set<String> materiasList;
    private Instant createdAt;


    public SimuladoSimplificadoDTO(Long id, Long qntPerguntas, String materias, String bancas,Instant createdAt) {
        this.id = id;
        this.qntPerguntas = qntPerguntas;
        this.materias = materias;
        this.bancas = bancas;
        this.createdAt  =createdAt;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQntPerguntas() {
        return qntPerguntas;
    }

    public void setQntPerguntas(Long qntPerguntas) {
        this.qntPerguntas = qntPerguntas;
    }

    public String getMaterias() {
        return materias;
    }

    public void setMaterias(String materias) {
        this.materias = materias;
    }

    public String getBancas() {
        return bancas;
    }

    public void setBancas(String bancas) {
        this.bancas = bancas;
    }

    public Set<String> getBancasList() {
        return Arrays.stream(this.bancas.split("\\|#\\|")).distinct().collect(Collectors.toSet());
    }

    public Set<String> getMateriasList() {
        return Arrays.stream(this.materias.split("\\|#\\|")).distinct().collect(Collectors.toSet());
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
