package com.conexxa.grupo_estudos.Service;

import com.conexxa.grupo_estudos.Model.Task;
import com.conexxa.grupo_estudos.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
}