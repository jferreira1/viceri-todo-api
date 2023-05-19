package com.viceri.app.controller;

import com.viceri.app.dto.TaskRequestDTO;
import com.viceri.app.dto.TaskDTO;
import com.viceri.app.enums.Priority;
import com.viceri.app.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskRequestDTO taskRequestDTO) {
        return new ResponseEntity<>(taskService.createTask(taskRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable UUID id, @RequestBody TaskRequestDTO taskRequestDTO) {
        return new ResponseEntity<>(taskService.updateTask(id, taskRequestDTO), HttpStatus.OK);
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<TaskDTO> completeTask(@PathVariable UUID id) {
        return new ResponseEntity<>(taskService.completeTask(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<TaskDTO>> listPendingTasks(@RequestParam(required = false) Priority priority) {
        return new ResponseEntity<>(taskService.listPendingTasks(priority), HttpStatus.OK);
    }
}
