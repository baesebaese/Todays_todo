package com.babobird.Toto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name="task")
@Getter
@Setter
@ToString
public class Task {

    @Id
    @Column(name = "task_no")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskNo; // 할 일 번호

    @Column(name="task_nm", nullable = false, length = 100)
    private String taskNm; // 할 일 이름

    @Enumerated(EnumType.STRING)
    private TaskStatus status; // 진행 상태

    @Column(name = "write_dt")
    private LocalDateTime writeDt; // 작성일

    @Column(name = "modify_dt")
    private LocalDateTime modifyDt; // 수정일


}