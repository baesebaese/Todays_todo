package com.babobird.Toto.service;

import com.babobird.Toto.entity.Toto;
import com.babobird.Toto.repository.TotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotoService {

    private final TotoRepository totoRepository;

    public TotoService(TotoRepository totoRepository) {
        this.totoRepository = totoRepository;
    }

    // 할 일 목록 전체 조회
    public List<Toto> getAllTotos() {
        return totoRepository.findAll();
    }

    // 추가적으로 필요한 서비스 메소드들을 여기에 구현 가능
}