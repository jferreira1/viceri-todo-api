package com.viceri.app.service;

import com.viceri.app.dto.TaskRequestDTO;
import com.viceri.app.dto.TaskDTO;
import com.viceri.app.enums.Priority;
import com.viceri.app.mapper.TaskMapper;
import com.viceri.app.model.Task;
import com.viceri.app.repository.TaskRepository;
import com.viceri.app.repository.UserRepository;
import com.viceri.app.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, JwtService jwtService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskDTO createTask(TaskRequestDTO taskRequestDTO) {
        Task task = new Task(taskRequestDTO.getDescription(), taskRequestDTO.getPriority());
        task.setCompleted(false);
        String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByEmail(emailUser);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        task.setUser(user.get());
        Task createdTask = taskRepository.save(task);

        return TaskMapper.toDTO(createdTask);
    }

    public TaskDTO updateTask(UUID id, TaskRequestDTO taskRequestDTO) throws Exception {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found."));
        String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByEmail(emailUser);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        if(!task.getUser().equals(user.get())) {
            throw new Exception("Forbidden user access");
        };
        task.setDescription(taskRequestDTO.getDescription());
        task.setPriority(taskRequestDTO.getPriority());
        Task taskSaved = taskRepository.save(task);

        return TaskMapper.toDTO(taskSaved);
    }

    public TaskDTO completeTask(UUID id) throws Exception {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found."));

        String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByEmail(emailUser);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        if(!task.getUser().equals(user.get())) {
            throw new Exception("Forbidden user access");
        };

        task.setCompleted(true);
        Task taskSaved = taskRepository.save(task);

        return TaskMapper.toDTO(taskSaved);
    }

    public void deleteTask(UUID id) throws Exception {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found."));

        String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByEmail(emailUser);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        if(!task.getUser().equals(user.get())) {
            throw new Exception("Forbidden user access");
        };

        taskRepository.deleteById(id);
    }

    public List<TaskDTO> listPendingTasks(Priority priority) {
        String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByEmail(emailUser);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }

        if (priority == null) {
            return TaskMapper.toDTOList(taskRepository.findAllIncompleteByUser(user.get()));
        }

        return TaskMapper.toDTOList(taskRepository.findAllIncompleteByPriorityAndUser(priority, user.get()));
    }
}