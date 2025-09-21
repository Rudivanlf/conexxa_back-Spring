package com.conexxa.grupo_estudos.DTO;

import com.conexxa.grupo_estudos.DTO.GroupSummaryDTO;
import com.conexxa.grupo_estudos.Model.User;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String curso;
    private LocalDateTime dataCriacao;
    private Set<GroupSummaryDTO> grupos; // Usando o DTO de resumo aqui!

    public UserDetailResponseDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.curso = user.getCurso();
        this.dataCriacao = user.getDataCriacao();
        // Converte a lista de entidades Group para uma lista de DTOs GroupSummaryDTO
        this.grupos = user.getGrupos().stream()
                .map(GroupSummaryDTO::new)
                .collect(Collectors.toSet());
    }

    // Gere os Getters e Setters para todos os campos
    // ...

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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Set<GroupSummaryDTO> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<GroupSummaryDTO> grupos) {
        this.grupos = grupos;
    }
}
