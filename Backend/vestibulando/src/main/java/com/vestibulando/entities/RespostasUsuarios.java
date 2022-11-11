package com.vestibulando.entities;

import javax.persistence.*;
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
    private List<Resposta> respostas;

    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "simulado_id",nullable = false)
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
}
