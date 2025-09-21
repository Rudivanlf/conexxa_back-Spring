package com.conexxa.grupo_estudos.DTO;

public class UserRequestDTO {

    private String nome;
    private String email;
    private String senhaHash;
    private String curso;

    // Gere os Getters e Setters para todos os campos abaixo
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
