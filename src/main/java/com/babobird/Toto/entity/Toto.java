package com.babobird.Toto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="toto")
@Getter
@Setter
@ToString
public class Toto {

    @Id
    @Column(name="toto_no")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int totoNo; // 리스트 번호

    @Column(name="toto_nm", nullable = false, length = 100)
    private String totoNm; // 리스트 이름

//    @ManyToOne
//    @JoinColumn(name="task_id")
//    private Task task;

}