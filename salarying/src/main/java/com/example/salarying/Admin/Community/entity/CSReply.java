package com.example.salarying.Admin.Community.entity;


import com.example.salarying.Admin.User.entity.Admin;
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
@Table(name = "cs_reply")
public class CSReply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="reply_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="admin_id")
    private Admin admin;

    @Column(name = "reply_content")
    private String answer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;


}
