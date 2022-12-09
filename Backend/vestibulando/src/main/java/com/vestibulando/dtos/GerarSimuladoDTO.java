package com.vestibulando.dtos;

import com.vestibulando.entities.Banca;
import com.vestibulando.entities.Materia;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class GerarSimuladoDTO {
    @Min(value = 5,message = "O simulado deve possuir no minimo 5 perguntas")
    @Max(value=90,message = "O simulado pode possuir no máximo 90 perguntas")
    private int NumeroPerguntas;
    private Set<Banca> bancas;
    @NotNull(message = "Selecione ao menos uma matéria para o simulado")
    private Set<Materia> materias;

    public GerarSimuladoDTO() {
    }
    public GerarSimuladoDTO(int numeroPerguntas, Set<Banca> bancas, Set<Materia> materias) {
        NumeroPerguntas = numeroPerguntas;
        this.bancas = bancas;
        this.materias = materias;
    }

    public int getNumeroPerguntas() {
        return NumeroPerguntas;
    }
    public void setNumeroPerguntas(int numeroPerguntas) {
        NumeroPerguntas = numeroPerguntas;
    }
    public Set<Banca> getBancas() {
        return bancas;
    }
    public void setBancas(Set<Banca> bancas) {
        this.bancas = bancas;
    }
    public Set<Materia> getMaterias() {
        return materias;
    }
    public void setMaterias(Set<Materia> materias) {
        this.materias = materias;
    }
}
