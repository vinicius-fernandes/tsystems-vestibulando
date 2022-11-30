package com.vestibulando.dtos;

import com.vestibulando.entities.Banca;
import com.vestibulando.entities.Materia;
import com.vestibulando.entities.Simulado;
import java.util.Set;
import java.util.stream.Collectors;

public class SimuladoDTO {
    private Set<Materia> materias;
    private Set<Banca> bancas;
    private Set<PerguntaDTO> perguntas;
    private long id;

    public SimuladoDTO() {
    }
    public SimuladoDTO(Simulado simulado){
        setId(simulado.getId());
        setBancas(simulado.getBancas());
        setMaterias(simulado.getMaterias());
        setPerguntas(simulado.getPerguntas().stream().map(PerguntaDTO::new).collect(Collectors.toSet()));
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
    public Set<PerguntaDTO> getPerguntas() {
        return perguntas;
    }
    public void setPerguntas(Set<PerguntaDTO> perguntas) {
        this.perguntas = perguntas;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
