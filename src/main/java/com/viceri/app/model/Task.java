package com.viceri.app.model;

import com.viceri.app.enums.Priority;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(nullable = false)
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Task() {
    }

    public Task(String description, Priority priority, boolean isCompleted, User user) {
        this.description = description;
        this.priority = priority;
        this.isCompleted = isCompleted;
        this.user = user;
    }

    public UUID getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isCompleted == task.isCompleted && Objects.equals(id, task.id) && Objects.equals(description, task.description) && priority == task.priority && Objects.equals(user, task.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, priority, isCompleted, user);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", isCompleted=" + isCompleted +
                ", user=" + user +
                '}';
    }
}
