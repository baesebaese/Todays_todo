package com.babobird.Toto.repository;

import com.babobird.Toto.entity.Task;
import com.babobird.Toto.entity.TaskStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    @DisplayName("할 일 저장 테스트")
    public void createTaskTest(){
        Task task = new Task();
        task.setTaskNm("오늘의 할 일");
        task.setStatus(TaskStatus.N);
        task.setWriteDt(LocalDateTime.now());
        task.setModifyDt(LocalDateTime.now());
        Task savedTask = taskRepository.save(task);
        System.out.println(savedTask.toString());
    }

    public void createTaskList() {
        for(int i=0; i<=10; i++) {
            Task task = new Task();
            task.setTaskNm("오늘의 할 일" + i);
            task.setStatus(TaskStatus.N);
            task.setWriteDt(LocalDateTime.now());
            task.setModifyDt(LocalDateTime.now());
            Task savedTask = taskRepository.save(task);
        }
    }

    @Test
    @DisplayName("할 일 조회 테스트")
    public void findByTaskNoTest() {
        this.createTaskList();
        Task task = taskRepository.findByTaskNo(1);

        System.out.println(task.toString());

    }

}