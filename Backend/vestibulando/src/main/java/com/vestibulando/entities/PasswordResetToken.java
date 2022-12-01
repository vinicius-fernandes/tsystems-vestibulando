package com.vestibulando.entities;

import org.apache.catalina.User;

import javax.persistence.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
public class PasswordResetToken {

    public static final long EXPIRATION = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Usuario user;

    private Instant expiryDate;

    public PasswordResetToken() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public PasswordResetToken( String token, Usuario user) {

        this.token = token;
        this.user = user;
        this.expiryDate = Instant.now().plus(EXPIRATION, ChronoUnit.MINUTES);
    }

    public boolean isTokenValid(){
        return Instant.now().isBefore(this.expiryDate);
    }
}
