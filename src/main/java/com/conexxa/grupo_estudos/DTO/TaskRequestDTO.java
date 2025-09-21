package com.conexxa.grupo_estudos.DTO;

import java.time.LocalDateTime;

public class TaskRequestDTO {

    private String titulo;
    private String descricao;
    private LocalDateTime dataEntrega;
    private String status;

    // Gere os Getters e Setters para todos os campos
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDateTime getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDateTime dataEntrega) { this.dataEntrega = dataEntrega; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
