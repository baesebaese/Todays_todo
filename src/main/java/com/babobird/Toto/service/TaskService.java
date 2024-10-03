package com.babobird.Toto.service;

import com.babobird.Toto.dto.TaskFormDto;
import com.babobird.Toto.entity.Task;
import com.babobird.Toto.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public TaskFormDto getTaskDtl(int taskNo) {
        Task task = taskRepository.findByTaskNo(taskNo);
        TaskFormDto taskFormDto = TaskFormDto.of(task);

        return taskFormDto;
    }

    public int updateTask(TaskFormDto taskFormDto) throws Exception {
        // task 수정
        Task task = taskRepository.findByTaskNo(taskFormDto.getTaskNo());
        task.updateTask(taskFormDto);

        return task.getTaskNo();
    }
}