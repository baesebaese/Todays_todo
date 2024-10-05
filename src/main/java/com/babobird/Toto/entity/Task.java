package com.babobird.Toto.entity;

import com.babobird.Toto.dto.TaskFormDto;
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
public class Task{

    @Id
    @Column(name = "task_no")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskNo; // 할 일 번호

    @Column(name="task_nm", nullable = false, length = 100)
    private String taskNm; // 할 일 이름

    @Enumerated(EnumType.STRING)
    @Column(name="task_status")
            private TaskStatus status; // 진행 상태

    @Column(name = "write_dt")
    private LocalDateTime writeDt; // 작성일

    @Column(name = "modify_dt")
    private LocalDateTime modifyDt; // 수정일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toto_no")
    private Toto toto;

    public int getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(int taskNo) {
        this.taskNo = taskNo;
    }

    public String getTaskNm() {
        return taskNm;
    }

    public void setTaskNm(String taskNm) {
        this.taskNm = taskNm;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getWriteDt() {
        return writeDt;
    }

    public void setWriteDt(LocalDateTime writeDt) {
        this.writeDt = writeDt;
    }

    public LocalDateTime getModifyDt() {
        return modifyDt;
    }

    public void setModifyDt(LocalDateTime modifyDt) {
        this.modifyDt = modifyDt;
    }

    public Toto getToto() {
        return toto;
    }

    public void setToto(Toto toto) {
        this.toto = toto;
    }
}