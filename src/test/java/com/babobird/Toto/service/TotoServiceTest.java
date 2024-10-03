package com.babobird.Toto.service;

import com.babobird.Toto.entity.Toto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TotoServiceTest {

    @Autowired
    private TotoService totoService;

    @Test
    @Transactional
    public void testGetAllTodos() {
        // 할 일 목록을 조회합니다.
        List<Toto> todos = totoService.getAllTotos();

        // 데이터베이스에 저장된 목록이 비어있지 않은지 확인
        assertFalse(todos.isEmpty(), "The todo list should not be empty");

        // 조회된 데이터 출력
        System.out.println("=== 조회된 할 일 목록 ===");
        todos.forEach(todo -> {
            System.out.println("Todo ID: " + todo.getTotoNo());
            System.out.println("Todo Name: " + todo.getTotoNm());
            System.out.println("Task ID: " + todo.getTask().getTaskNo());
            System.out.println("--------------------------");
        });

        // 특정 데이터가 있는지 확인 (예: "Task 1"이 존재하는지)
        assertTrue(todos.stream().anyMatch(todo -> todo.getTotoNm().equals("Task 1")));
    }
}