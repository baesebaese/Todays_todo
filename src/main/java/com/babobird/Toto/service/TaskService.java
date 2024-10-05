package com.babobird.Toto.service;


import com.babobird.Toto.entity.Task;
import com.babobird.Toto.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksByTotoNo(int totoNo) {
        return taskRepository.findByToto_TotoNo(totoNo);
    }
}
