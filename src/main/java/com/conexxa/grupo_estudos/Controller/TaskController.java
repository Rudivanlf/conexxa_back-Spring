package com.conexxa.grupo_estudos.Controller;

import com.conexxa.grupo_estudos.DTO.TaskResponseDTO;
import com.conexxa.grupo_estudos.Model.Task;
import com.conexxa.grupo_estudos.Service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.conexxa.grupo_estudos.DTO.TaskRequestDTO;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Criar uma nova tarefa para um grupo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Grupo com o ID informado não foi encontrado")
    })
    @PostMapping("/groups/{groupId}/tasks")
    public TaskResponseDTO createTask(@PathVariable Long groupId, @RequestBody TaskRequestDTO taskRequest) {
        Task newTask = taskService.createTask(groupId, taskRequest);
        return new TaskResponseDTO(newTask);
    }

    @Operation(summary = "Listar todas as tarefas de um grupo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado")
    })
    @GetMapping("/groups/{groupId}/tasks")
    public List<TaskResponseDTO> getAllTasksByGroup(@PathVariable Long groupId) {
        return taskService.getAllTasksByGroupId(groupId).stream()
                .map(TaskResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Buscar uma tarefa pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa com o ID informado não foi encontrada")
    })
    @GetMapping("/tasks/{taskId}")
    public TaskResponseDTO getTaskById(@PathVariable Long taskId) {
        return new TaskResponseDTO(taskService.getTaskById(taskId));
    }

    @Operation(summary = "Atualizar uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PutMapping("/tasks/{taskId}")
    public TaskResponseDTO updateTask(@PathVariable Long taskId, @RequestBody TaskRequestDTO taskRequest) {
        Task updatedTask = taskService.updateTask(taskId, taskRequest);
        return new TaskResponseDTO(updatedTask);
    }

    @Operation(summary = "Deletar uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }
}