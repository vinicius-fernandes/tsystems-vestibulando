package com.vestibulando.dtos;

import com.vestibulando.entities.Role;
import com.vestibulando.entities.Usuario;
import java.util.Set;

public class UsuarioDTO {
    private Long id;
    private String email;
    private String nome;
    private Usuario usuario;
    private Set<Role> roles;

    public UsuarioDTO() {
    }
    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.roles = usuario.getRoles();
    }
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
}
