package com.vestibulando.entities;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.vestibulando.enums.EnumsUsuario;
import org.hibernate.annotations.Check;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.zip.DataFormatException;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email(message = "E-mail invalido")
    @NotBlank(message = "E-mail não pode ser em branco")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Senha deve conter no minimo 6 caracteres")
    @Size(message = "Senha deve conter no minimo 6 caracteres", min = 6)
    @Pattern(regexp = "[^\\ ]+", message="Senha não pode conter espaços")
    private String senha;
    @NotBlank(message = "O nome não pode ser em branco")
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
        return (createdAt!=null)? createdAt.atZone(ZoneId.systemDefault().normalized()).toLocalDateTime():null;
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
