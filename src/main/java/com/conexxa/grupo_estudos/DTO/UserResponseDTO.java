package com.conexxa.grupo_estudos.DTO;

import com.conexxa.grupo_estudos.Model.User;

public class UserResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String curso;

    // Construtor que converte a entidade User para o DTO
    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.curso = user.getCurso();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}