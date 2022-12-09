package com.vestibulando.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToMany(mappedBy = "materias")
    private Set<Simulado> simulado;
    @Size(message = "O nome da matéria deve ser maior que 2 caracteres e menor que 100", min = 2, max = 100)
    @NotBlank(message = "O nome da matéria deve ser maior que 2 caracteres e menor que 100")
    @NotEmpty(message = "O nome da matéria deve ser maior que 2 caracteres e menor que 100")
    @NotNull(message = "O nome da matéria deve ser maior que 2 caracteres e menor que 100")
    private String nome;

    @JsonIgnore
    @OneToMany(
            mappedBy = "materia",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Pergunta> perguntas = new HashSet<>();

    public String getNome() {
        return nome;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Set<Simulado> getSimulado() {
        return simulado;
    }
    public void setSimulado(Set<Simulado> simulado) {
        this.simulado = simulado;
    }
}
