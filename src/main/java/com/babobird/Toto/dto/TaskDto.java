package com.babobird.Toto.dto;

import com.babobird.Toto.entity.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDto {

    private int taskNo;

    private String taskName;

    private TaskStatus taskStatus;

    private LocalDateTime createDt;

    private LocalDateTime modifyDt;

}