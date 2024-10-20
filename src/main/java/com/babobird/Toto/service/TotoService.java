package com.babobird.Toto.service;

import com.babobird.Toto.dto.TotoWithProgress;
import com.babobird.Toto.entity.Task;
import com.babobird.Toto.entity.TaskStatus;
import com.babobird.Toto.entity.Toto;
import com.babobird.Toto.repository.TaskRepository;
import com.babobird.Toto.repository.TotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TotoService {

    private final TotoRepository totoRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    public TotoService(TotoRepository totoRepository, TaskRepository taskRepository, TaskService taskService) {
        this.totoRepository = totoRepository;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }


    // 할 일 목록 전체 조회
    public List<Toto> getAllTotos() {
        return totoRepository.findAll();
    }

    public List<TotoWithProgress> getAllTotosWithProgress() {
        List<Toto> totos = totoRepository.findAll();  // 모든 Todo 가져오기
        List<TotoWithProgress> todosWithProgress = new ArrayList<>();

        for (Toto toto : totos) {
            List<Task> tasks = taskRepository.findByToto_TotoNo(toto.getTotoNo());  // 해당 Todo의 Task 목록 가져오기
            int totalTasks = tasks.size();  // Task 전체 개수
            long completedTasks = tasks.stream().filter(task -> TaskStatus.Y.equals(task.getStatus())).count();  // 완료된 Task 수

            // 완료율 계산 (totalTasks가 0일 경우 0%로 설정)
            double progress = totalTasks > 0 ? (double) completedTasks / totalTasks * 100 : 0;
            progress = Math.round(progress * 10) / 10.0;

            // 진행율 정보와 함께 Todo를 전달하기 위한 DTO 생성
            todosWithProgress.add(new TotoWithProgress(toto, progress));
        }

        return todosWithProgress;

    }



    // totoNo에 해당하는 Todo 리스트를 가져오는 메서드
    public Toto getTotoById(int totoNo) {
        return totoRepository.findById(totoNo).orElse(null);  // 해당하는 Toto가 없으면 null 반환
    }

    // 리스트 저장 메서드
    public void saveToto(Toto toto) {
        totoRepository.save(toto);  // 레포지토리를 통해 리스트 저장
    }
    // 삭제 메서드
    @Transactional
    public void deleteTotoById(int totoNo) {
        // 데이터베이스에서 totoNo로 삭제
        if (totoRepository.existsById(totoNo)) {
            // 먼저 totoNo에 해당하는 모든 Task 삭제
            taskService.deleteTasksByTotoNo(totoNo);


            totoRepository.deleteById(totoNo);
        } else {
            throw new RuntimeException("해당 투두 항목이 존재하지 않습니다.");
        }
    }
}