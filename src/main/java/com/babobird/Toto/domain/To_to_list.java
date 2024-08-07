package com.babobird.Toto.domain;

import java.util.Date;

public class To_to_list {

    private int list_no; // 리스트 번호

    private String list_nm; // 리스트 이름

    private int task_no; // 할 일 번호

    private String task_nm; // 할 일 이름

    private String status; // 진행 상태

    private Date write_dt; // 작성일

    private Date modify_dt; // 수정일

    public int getList_no() {
        return list_no;
    }

    public void setList_no(int list_no) {
        this.list_no = list_no;
    }

    public String getList_nm() {
        return list_nm;
    }

    public void setList_nm(String list_nm) {
        this.list_nm = list_nm;
    }

    public int getTask_no() {
        return task_no;
    }

    public void setTask_no(int task_no) {
        this.task_no = task_no;
    }

    public String getTask_nm() {
        return task_nm;
    }

    public void setTask_nm(String task_nm) {
        this.task_nm = task_nm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getWrite_dt() {
        return write_dt;
    }

    public void setWrite_dt(Date write_dt) {
        this.write_dt = write_dt;
    }

    public Date getModify_dt() {
        return modify_dt;
    }

    public void setModify_dt(Date modify_dt) {
        this.modify_dt = modify_dt;
    }
}