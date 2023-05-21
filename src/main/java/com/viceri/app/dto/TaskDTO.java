package com.viceri.app.dto;

import com.viceri.app.enums.Priority;

import java.util.Objects;
import java.util.UUID;

public class TaskDTO {
    private UUID id;
    private String description;
    private Priority priority;
    private boolean isCompleted;

    public TaskDTO() {
    }
    public TaskDTO(UUID id, String description, Priority priority, boolean isCompleted) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.isCompleted = isCompleted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return isCompleted == taskDTO.isCompleted && Objects.equals(id, taskDTO.id) && Objects.equals(description, taskDTO.description) && priority == taskDTO.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, priority, isCompleted);
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
