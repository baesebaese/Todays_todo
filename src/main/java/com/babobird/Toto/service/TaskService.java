package com.babobird.Toto.service;

import com.babobird.Toto.dto.TaskFormDto;
import com.babobird.Toto.entity.Task;
import com.babobird.Toto.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public long saveTask(TaskFormDto taskFormDto) throws Exception {

        // task 등록
        Task task = taskFormDto.createTask();
        taskRepository.save(task);

        return task.getTaskNo();
    }

}