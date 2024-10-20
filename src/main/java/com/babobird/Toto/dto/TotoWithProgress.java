package com.babobird.Toto.dto;

import com.babobird.Toto.entity.Toto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotoWithProgress {
    private Toto toto;
    private double progress;  // 진행율 (0 ~ 100)

    public TotoWithProgress(Toto toto, double progress) {
        this.toto = toto;
        this.progress = progress;
    }
}