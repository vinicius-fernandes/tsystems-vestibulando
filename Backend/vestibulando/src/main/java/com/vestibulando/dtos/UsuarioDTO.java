package com.vestibulando.dtos;

import com.vestibulando.entities.Usuario;
import com.vestibulando.enums.EnumsUsuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioDTO {
    private Long id;
    private String email;
    private String nome;
    private Usuario usuario;
    private EnumsUsuario tipo;
    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.tipo = usuario.getTipo();
    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public EnumsUsuario getTipo() {
        return tipo;
    }

    public void setTipo(EnumsUsuario tipo) {
        this.tipo = tipo;
    }
}
