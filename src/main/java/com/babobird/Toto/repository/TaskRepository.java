package com.babobird.Toto.repository;

import com.babobird.Toto.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTaskNo(int taskNo);
}