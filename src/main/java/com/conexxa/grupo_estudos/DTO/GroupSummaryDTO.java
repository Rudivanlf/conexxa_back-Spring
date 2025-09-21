package com.conexxa.grupo_estudos.DTO;

import com.conexxa.grupo_estudos.Model.Group;

public class GroupSummaryDTO {

    private Long id;
    private String nome;
    private String descricao;

    public GroupSummaryDTO(Group group) {
        this.id = group.getId();
        this.nome = group.getNome();
        this.descricao = group.getDescricao();
    }

    // Gere os Getters e Setters para todos os campos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }


}
