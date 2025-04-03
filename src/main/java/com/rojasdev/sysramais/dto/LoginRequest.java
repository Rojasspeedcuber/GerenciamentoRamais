package com.rojasdev.sysramais.dto;

public class LoginRequest {
    private String usuarioNome;
    private String extension;

    public LoginRequest() {}

    public LoginRequest(String usuarioNome, String extension) {
        this.usuarioNome = usuarioNome;
        this.extension = extension;
    }

    // Getters
    public String getUsuarioNome() {
        return usuarioNome;
    }

    public String getExtension() {
        return extension;
    }

    // Setters
    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}

