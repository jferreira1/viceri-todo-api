package com.viceri.app.mapper;

import com.viceri.app.dto.TaskDTO;
import com.viceri.app.model.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {
    public static TaskDTO toDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setCompleted(task.isCompleted());

        return taskDTO;
    }

    public static List<TaskDTO> toDTOList(List<Task> tasks) {
        return tasks.stream().map(TaskMapper::toDTO).collect(Collectors.toList());
    }
}
