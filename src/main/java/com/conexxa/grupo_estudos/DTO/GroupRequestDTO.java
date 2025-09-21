package com.conexxa.grupo_estudos.DTO;

public class GroupRequestDTO {

    private String nome;
    private String descricao;
    private Long criadorId; // Apenas o ID do criador

    // Gere os Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Long getCriadorId() { return criadorId; }
    public void setCriadorId(Long criadorId) { this.criadorId = criadorId; }
}
