package com.example.salarying.Admin.Community.entity;

import com.example.salarying.Admin.Community.exception.CommunityException;
import com.example.salarying.Admin.Community.exception.CommunityExceptionType;
import com.example.salarying.Admin.User.entity.Admin;
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

    public void update(String title, String content, boolean status) {
        if (title.equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_TITLE);
        } else {
            this.title = title;
        }
        if (content.equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_CONTENT);
        } else {
            this.content = content;
        }
        this.status = status;
    }

}
