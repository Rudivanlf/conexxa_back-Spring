package com.conexxa.grupo_estudos.Controller;

import com.conexxa.grupo_estudos.Model.Task;
import com.conexxa.grupo_estudos.Service.TaskService;
import com.conexxa.grupo_estudos.DTO.TaskResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping() // Deixamos o RequestMapping base vazio para definir nos métodos
public class TaskController {

    @Autowired
    private TaskService taskService;

    // CREATE: Cria uma nova tarefa dentro de um grupo
    @PostMapping("/groups/{groupId}/tasks")
    public TaskResponseDTO createTask(@PathVariable Long groupId, @RequestBody Task task) {
        Task newTask = taskService.createTask(groupId, task);
        return new TaskResponseDTO(newTask);
    }

    // READ: Lista todas as tarefas de um grupo
    @GetMapping("/groups/{groupId}/tasks")
    public List<TaskResponseDTO> getAllTasksByGroup(@PathVariable Long groupId) {
        return taskService.getAllTasksByGroupId(groupId).stream()
                .map(TaskResponseDTO::new)
                .collect(Collectors.toList());
    }

    // READ: Busca uma tarefa específica pelo seu ID
    @GetMapping("/tasks/{taskId}")
    public TaskResponseDTO getTaskById(@PathVariable Long taskId) {
        return new TaskResponseDTO(taskService.getTaskById(taskId));
    }

    // UPDATE: Atualiza uma tarefa existente
    @PutMapping("/tasks/{taskId}")
    public TaskResponseDTO updateTask(@PathVariable Long taskId, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(taskId, taskDetails);
        return new TaskResponseDTO(updatedTask);
    }

    // DELETE: Remove uma tarefa
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build(); // Retorna uma resposta 200 OK sem corpo
    }
}