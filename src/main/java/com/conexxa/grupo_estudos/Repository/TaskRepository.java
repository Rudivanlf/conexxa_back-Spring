package com.conexxa.grupo_estudos.Repository;

import com.conexxa.grupo_estudos.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Importe a classe List

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Spring Data JPA cria a query automaticamente a partir do nome do method
    List<Task> findByGrupoId(Long grupoId);
}

