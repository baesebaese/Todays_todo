package com.babobird.Toto.service;


import com.babobird.Toto.entity.Task;
import com.babobird.Toto.entity.TaskId;
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

    //Task 단건 조회 메서드
    public Task getTaskById(int taskNo, int totoNo) {
        TaskId id = new TaskId(taskNo, totoNo);
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id: " + taskNo));
    }

    // Task를 저장하는 메서드
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    // Task 삭제 메서드
    public void deleteTaskById(int taskNo, int totoNo) {
        TaskId id = new TaskId(taskNo, totoNo);
        taskRepository.deleteById(id);
    }
}
