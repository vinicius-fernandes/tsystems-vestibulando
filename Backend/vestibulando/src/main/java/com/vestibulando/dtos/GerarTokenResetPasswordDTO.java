package com.vestibulando.dtos;

public class GerarTokenResetPasswordDTO {

    private String email;

    private String urlFrom;

    public GerarTokenResetPasswordDTO() {
    }

    public GerarTokenResetPasswordDTO(String email, String urlFrom) {
        this.email = email;
        this.urlFrom = urlFrom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlFrom() {
        return urlFrom;
    }

    public void setUrlFrom(String urlFrom) {
        this.urlFrom = urlFrom;
    }
}
