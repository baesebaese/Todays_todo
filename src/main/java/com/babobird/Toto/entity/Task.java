package com.babobird.Toto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@ToString
@IdClass(TaskId.class) // 복합 키 클래스 사용
public class Task {

    @Id
    @Column(name = "task_no")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskNo; // Task의 기본 키

    @Id
    @Column(name = "toto_no")  // 외래 키로 사용될 toto_no를 @Id로 선언
    private int totoNo;  // 복합 키에 포함될 totoNo (Toto 엔티티의 totoNo 필드와 매핑)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toto_no", referencedColumnName = "toto_no", insertable = false, updatable = false)
    private Toto toto;  // 외래 키로 Toto 엔티티 참조

    @Column(name = "task_nm", nullable = false, length = 100)
    private String taskNm; // 할 일 이름

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus status; // 진행 상태

    @Column(name = "write_dt")
    private LocalDateTime writeDt; // 작성일

    @Column(name = "modify_dt")
    private LocalDateTime modifyDt; // 수정일

    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = TaskStatus.N;  // 기본 상태 "N"으로 설정
        }
        writeDt = LocalDateTime.now();
        modifyDt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        if (status == null) {
            status = TaskStatus.N;  // 기본 상태 "N"으로 설정
        }
        modifyDt = LocalDateTime.now();
    }
}
