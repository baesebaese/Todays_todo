package com.babobird.Toto.dto;

import com.babobird.Toto.entity.Task;
import com.babobird.Toto.entity.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskFormDto {


    private int taskNo; // 할 일 번호

    private String taskNm; // 할 일 이름

    private TaskStatus status; // 진행 상태

    private LocalDateTime writeDt; // 작성일

    private LocalDateTime modifyDt; // 수정일

    private static ModelMapper modelMapper = new ModelMapper();

    public Task createTask() {
        return modelMapper.map(this, Task.class);
    }

    public static TaskFormDto of(Task task) {
        return modelMapper.map(task, TaskFormDto.class);
    }

}