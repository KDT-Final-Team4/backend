package com.example.salarying.Corporation.Community.entity;



import com.example.salarying.Admin.Community.entity.CSReply;
import com.example.salarying.Corporation.User.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cs")
public class CS {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cs_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cs_id")
    private List<CSReply> replies = new ArrayList<>();


}
