package com.conexxa.grupo_estudos.DTO;

import com.conexxa.grupo_estudos.DTO.UserResponseDTO;
import com.conexxa.grupo_estudos.Model.Group;
import com.conexxa.grupo_estudos.Model.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private UserResponseDTO criador;
    private LocalDateTime dataCriacao;
    private Set<UserResponseDTO> membros;
    private List<Task> tarefas;

    // Construtor que converte a entidade Group para o DTO
    public GroupResponseDTO(Group group) {
        this.id = group.getId();
        this.nome = group.getNome();
        this.descricao = group.getDescricao();
        this.criador = new UserResponseDTO(group.getCriador());
        this.dataCriacao = group.getDataCriacao();
        this.membros = group.getMembros().stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toSet());
        this.tarefas = group.getTarefas();
    }

    // Getters e Setters
    // (Pode gerar os getters e setters para todos os campos)

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UserResponseDTO getCriador() {
        return criador;
    }

    public void setCriador(UserResponseDTO criador) {
        this.criador = criador;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Set<UserResponseDTO> getMembros() {
        return membros;
    }

    public void setMembros(Set<UserResponseDTO> membros) {
        this.membros = membros;
    }

    public List<Task> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Task> tarefas) {
        this.tarefas = tarefas;
    }
}