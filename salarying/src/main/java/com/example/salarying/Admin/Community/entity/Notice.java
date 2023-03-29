package com.example.salarying.Admin.Community.entity;

import com.example.salarying.Admin.User.entity.Admin;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;

    @Column(name = "status")
    private Boolean status;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void statusUpdate(Boolean status) {
        this.status = status;
    }

}
