package com.viceri.app.service;

import com.viceri.app.dto.TaskRequestDTO;
import com.viceri.app.dto.TaskDTO;
import com.viceri.app.enums.Priority;
import com.viceri.app.mapper.TaskMapper;
import com.viceri.app.model.Task;
import com.viceri.app.model.User;
import com.viceri.app.repository.TaskRepository;
import com.viceri.app.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskDTO createTask(TaskRequestDTO taskRequestDTO) {

        Task task = new Task(taskRequestDTO.getDescription(), taskRequestDTO.getPriority());
        task.setCompleted(false);
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
         task.setUser(users.get(0));
        }

        Task createdTask = taskRepository.save(task);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(createdTask.getId());
        taskDTO.setDescription(createdTask.getDescription());
        taskDTO.setPriority(createdTask.getPriority());
        taskDTO.setCompleted(createdTask.isCompleted());

        return taskDTO;
    }

    public TaskDTO updateTask(UUID id, TaskRequestDTO taskRequestDTO) {

        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found."));

        task.setDescription(taskRequestDTO.getDescription());
        task.setPriority(taskRequestDTO.getPriority());

        taskRepository.save(task);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setCompleted(task.isCompleted());

        return taskDTO;
    }

    public TaskDTO completeTask(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found."));

        task.setCompleted(true);
        taskRepository.save(task);
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setCompleted(task.isCompleted());

        return taskDTO;
    }

    public void deleteTask(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found."));
        taskRepository.deleteById(id);
    }

    public List<TaskDTO> listPendingTasks(Priority priority) {
        if (priority == null) {
            return TaskMapper.toDTOList(taskRepository.findAll());
        }
        return TaskMapper.toDTOList(taskRepository.findAllByPriority(priority));
    }
}
