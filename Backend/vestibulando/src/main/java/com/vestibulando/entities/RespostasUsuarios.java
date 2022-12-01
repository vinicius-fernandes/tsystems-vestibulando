package com.vestibulando.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.List;

@Entity
@Table(uniqueConstraints= {@UniqueConstraint(
        columnNames = {"usuario_id","simulado_id"})})
public class RespostasUsuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Instant created_at;
    private Instant updated_at;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "Respostas_has_resultadosSimulados",
            joinColumns = @JoinColumn(name = "resultadossimulados_id"),
            inverseJoinColumns = @JoinColumn(name = "resposta_id"))
    @NotNull(message="A resposta do usuario deve conter as respostas marcadas pelo usuário")
    private List<Resposta> respostas;

    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false)
    @NotNull(message = "A resposta do usuario deve estar associada a um usuário")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "simulado_id",nullable = false)
    @NotNull(message = "A resposta do usuario deve estar associada a um simulado")
    private Simulado simulado;

    @PrePersist
    public void prePersist(){
        this.created_at = Instant.now();


    }

    @PreUpdate
    public void preUpdate(){
        this.updated_at = Instant.now();
    }




    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Simulado getSimulado() {
        return simulado;
    }

    public void setSimulado(Simulado simulado) {
        this.simulado = simulado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }
}
