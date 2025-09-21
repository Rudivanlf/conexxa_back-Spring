package com.conexxa.grupo_estudos.DTO;

public class LoginRequestDTO {

    private String email;
    private String senha; // O nome do campo deve corresponder ao JSON do frontend

    // Gere os Getters e Setters
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
}