package com.vestibulando.dtos;

import java.util.ArrayList;
import java.util.List;

public class PesquisaSimuladoDTO {

    private List<Long> idMaterias;

    private List<Long> idBancas;

    public PesquisaSimuladoDTO() {
    }

    public PesquisaSimuladoDTO(List<Long> idMaterias, List<Long> idBancas) {
        this.idMaterias = idMaterias;
        this.idBancas = idBancas;
    }

    public List<Long> getIdMaterias() {
        return idMaterias;
    }

    public void setIdMaterias(List<Long> idMaterias) {
        this.idMaterias = idMaterias;
    }

    public List<Long> getIdBancas() {
        return idBancas;
    }

    public void setIdBancas(List<Long> idBancas) {
        this.idBancas = idBancas;
    }

    public boolean valid(){
        if(this.getIdMaterias()==null)
            this.setIdMaterias(new ArrayList<>());
        if(this.getIdBancas()==null)
            this.setIdBancas(new ArrayList<>());

        return this.idBancas.size()>0 || this.idMaterias.size()>0;
    }
}
