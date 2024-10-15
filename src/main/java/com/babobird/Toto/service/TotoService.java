package com.babobird.Toto.service;

import com.babobird.Toto.entity.Toto;
import com.babobird.Toto.repository.TotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotoService {

    private final TotoRepository totoRepository;
    private final TaskService taskService;

    public TotoService(TotoRepository totoRepository, TaskService taskService) {
        this.totoRepository = totoRepository;
        this.taskService = taskService;
    }


    // 할 일 목록 전체 조회
    public List<Toto> getAllTotos() {
        return totoRepository.findAll();
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