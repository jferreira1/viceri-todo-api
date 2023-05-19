package com.viceri.app.repository;

import com.viceri.app.enums.Priority;
import com.viceri.app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAllByPriority(Priority priority);
}
