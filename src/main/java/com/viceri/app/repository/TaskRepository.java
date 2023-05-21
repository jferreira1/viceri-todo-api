package com.viceri.app.repository;

import com.viceri.app.enums.Priority;
import com.viceri.app.model.Task;
import com.viceri.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAllByUser(User user);
    List<Task> findAllByPriorityAndUser(Priority priority, User user);

    @Query("SELECT t FROM Task t JOIN t.user u WHERE t.isCompleted = false AND u = ?1")
    List<Task> findAllIncompleteByUser(User user);

    @Query("SELECT t FROM Task t JOIN t.user u WHERE t.isCompleted = false AND t.priority = ?1 AND u = ?2")
    List<Task> findAllIncompleteByPriorityAndUser(Priority priority, User user);

}