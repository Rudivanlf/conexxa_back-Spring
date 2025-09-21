package com.conexxa.grupo_estudos.Service;

import com.conexxa.grupo_estudos.Model.Group;
import com.conexxa.grupo_estudos.Model.Task;
import com.conexxa.grupo_estudos.Repository.GroupRepository;
import com.conexxa.grupo_estudos.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private GroupRepository groupRepository; // Injetar para encontrar o grupo

    // --- CREATE ---
    // Cria uma tarefa associada a um grupo específico
    public Task createTask(Long groupId, Task task) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado para criar a tarefa!"));
        task.setGrupo(group);
        return taskRepository.save(task);
    }

    // --- READ ---
    // Retorna todas as tarefas de um grupo específico
    public List<Task> getAllTasksByGroupId(Long groupId) {
        return taskRepository.findByGrupoId(groupId); // Precisaremos criar este método no repositório
    }

    // Retorna uma tarefa específica pelo seu ID
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));
    }

    // --- UPDATE ---
    // Atualiza uma tarefa existente
    public Task updateTask(Long taskId, Task taskDetails) {
        Task existingTask = getTaskById(taskId); // Reutiliza o método de busca

        existingTask.setTitulo(taskDetails.getTitulo());
        existingTask.setDescricao(taskDetails.getDescricao());
        existingTask.setDataEntrega(taskDetails.getDataEntrega());
        existingTask.setStatus(taskDetails.getStatus());

        return taskRepository.save(existingTask);
    }

    // --- DELETE ---
    // Deleta uma tarefa pelo seu ID
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Tarefa não encontrada para exclusão!");
        }
        taskRepository.deleteById(taskId);
    }
}