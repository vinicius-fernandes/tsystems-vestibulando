package com.vestibulando.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "materias")
    private List<Simulado> simulado;
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Simulado> getSimulado() {
        return simulado;
    }

    public void setSimulado(List<Simulado> simulado) {
        this.simulado = simulado;
    }
}
