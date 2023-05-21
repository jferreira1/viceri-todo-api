package com.viceri.app.dto;

import com.viceri.app.enums.Priority;

import java.util.Objects;

public class TaskRequestDTO {
    private String description;
    private Priority priority;

    public TaskRequestDTO() {
    }

    public TaskRequestDTO(String description, Priority priority) {
        this.description = description;
        this.priority = priority;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskRequestDTO that = (TaskRequestDTO) o;
        return Objects.equals(description, that.description) && priority == that.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, priority);
    }

    @Override
    public String toString() {
        return "TaskRequestDTO{" +
                "description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }
}
