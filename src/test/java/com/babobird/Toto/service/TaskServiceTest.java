package com.babobird.Toto.service;

import com.babobird.Toto.entity.Task;
import com.babobird.Toto.entity.TaskId;
import com.babobird.Toto.entity.TaskStatus;
import com.babobird.Toto.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void testSaveTask() {
        Task task = new Task();
        task.setTaskNm("Test Task");
        task.setStatus(TaskStatus.Y);

        // TaskRepository.save 메서드가 정상적으로 호출되는지 확인
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);

        taskService.saveTask(task);

        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
    }

    @Test
    public void testGetTaskById() {
        // TaskId를 사용하여 복합 키 설정
        TaskId taskId = new TaskId(1, 1);
        Task task = new Task();
        task.setTaskNo(1);
        task.setTaskNm("Task 1");
        task.setStatus(TaskStatus.N);

        // TaskRepository.findById 메서드가 Task를 반환하도록 설정
        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // TaskService의 getTaskById 호출
        Task foundTask = taskService.getTaskById(1, 1);

        // 결과 검증
        assertNotNull(foundTask);
        assertEquals(1, foundTask.getTaskNo());
        assertEquals("Task 1", foundTask.getTaskNm());
        assertEquals(TaskStatus.N, foundTask.getStatus());

        // TaskRepository.findById 메서드가 한 번 호출되었는지 검증
        Mockito.verify(taskRepository, Mockito.times(1)).findById(taskId);
    }


    @Test
    public void testDeleteTaskById() {
        // TaskId를 사용하여 복합 키 설정
        TaskId taskId = new TaskId(1, 1);

        // TaskService의 deleteTaskById 호출
        taskService.deleteTaskById(1, 1);

        // TaskRepository.deleteById 메서드가 한 번 호출되었는지 검증
        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(taskId);
    }

}