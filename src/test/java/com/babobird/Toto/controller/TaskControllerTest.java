package com.babobird.Toto.controller;

import com.babobird.Toto.dto.TaskFormDto;
import com.babobird.Toto.entity.Task;
import com.babobird.Toto.entity.TaskStatus;
import com.babobird.Toto.repository.TaskRepository;
import com.babobird.Toto.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class TaskControllerTest {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @Test
    @DisplayName("task 등록 테스트")
    void saveTask() throws Exception {
        TaskFormDto taskFormDto = new TaskFormDto();
        taskFormDto.setTaskNm("할 일 등록 테스트");
        taskFormDto.setStatus(TaskStatus.N);

        Long taskId = taskService.saveTask(taskFormDto);
        Task task = taskRepository.findById(taskId).orElseThrow(EntityNotFoundException::new);

        assertEquals(task.getTaskNm(), taskFormDto.getTaskNm());




    }


}