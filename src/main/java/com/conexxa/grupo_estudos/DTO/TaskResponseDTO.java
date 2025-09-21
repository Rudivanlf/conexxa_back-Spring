package com.conexxa.grupo_estudos.DTO;

import com.conexxa.grupo_estudos.Model.Task;
import java.time.LocalDateTime;

public class TaskResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataEntrega;
    private String status;
    private Long idGrupo; // Apenas o ID do grupo para simplicidade

    public TaskResponseDTO(Task task) {
        this.id = task.getId();
        this.titulo = task.getTitulo();
        this.descricao = task.getDescricao();
        this.dataEntrega = task.getDataEntrega();
        this.status = task.getStatus();
        this.idGrupo = task.getGrupo().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }
}