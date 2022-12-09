package com.vestibulando.dtos;

public class NotaSimuladoUsuarioDTO {
    private long idSimulado;
    private long nota;

    public NotaSimuladoUsuarioDTO() {
    }
    public NotaSimuladoUsuarioDTO(long idSimulado, long nota) {
        this.idSimulado = idSimulado;
        this.nota = nota;
    }

    public long getIdSimulado() {
        return idSimulado;
    }
    public void setIdSimulado(long idSimulado) {
        this.idSimulado = idSimulado;
    }
    public long getNota() {
        return nota;
    }
    public void setNota(long nota) {
        this.nota = nota;
    }
}
