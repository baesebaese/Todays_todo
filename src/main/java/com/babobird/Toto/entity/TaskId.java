package com.babobird.Toto.entity;

import java.io.Serializable;
import java.util.Objects;

public class TaskId implements Serializable {

    private int taskNo;
    private int totoNo;

    // 기본 생성자
    public TaskId() {}

    // 매개변수가 있는 생성자
    public TaskId(int taskNo, int totoNo) {
        this.taskNo = taskNo;
        this.totoNo = totoNo;
    }

    // Getters, Setters, equals, hashCode 메서드
    public int getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(int taskNo) {
        this.taskNo = taskNo;
    }

    public int getTotoNo() {
        return totoNo;
    }

    public void setTotoNo(int totoNo) {
        this.totoNo = totoNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskId taskId = (TaskId) o;
        return taskNo == taskId.taskNo && totoNo == taskId.totoNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskNo, totoNo);
    }
}
