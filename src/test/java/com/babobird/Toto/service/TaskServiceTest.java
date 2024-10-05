package com.babobird.Toto.service;

import com.babobird.Toto.entity.Task;
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

}