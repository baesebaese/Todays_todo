package com.babobird.Toto.repository;

import com.babobird.Toto.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    // Toto의 totoId를 기준으로 Task 목록을 조회하는 메서드
    List<Task> findByToto_TotoNo(int totoNo);
}