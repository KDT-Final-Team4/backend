package com.example.salarying.Corporation.Recruiting.entity;


import com.example.salarying.Corporation.User.entity.Member;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "recruiting")
public class Recruiting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="recruiting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @NotNull
    @Column(name="recruiting_title")
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;

    @Column(name="task")
    private String task;


    @Column(name="status")
    private String status;
}
