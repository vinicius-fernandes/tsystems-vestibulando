package com.vestibulando.entities;

import com.vestibulando.enums.EnumsUsuario;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(message = "Senha deve conter no minimo 6 caracteres", min = 6)
    @Pattern(regexp = "[^\\ ]+", message="Senha não pode conter espaços")
    private String senha;
    @NotBlank
    private String nome;
    private EnumsUsuario tipo;
    @Column(columnDefinition = "DATETIME")
    private Instant createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt.atZone(ZoneId.systemDefault().normalized()).toLocalDateTime();

    }

    public LocalDateTime getUpdatedAt() {
        return (updatedAt!=null)?updatedAt.atZone(ZoneId.systemDefault().normalized()).toLocalDateTime():null;

    }

    @Column(columnDefinition = "DATETIME")
    private Instant updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnumsUsuario getTipo() {
        return tipo;
    }

    public void setTipo(EnumsUsuario tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
