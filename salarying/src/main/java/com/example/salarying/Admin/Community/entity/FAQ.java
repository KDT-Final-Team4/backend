package com.example.salarying.Admin.Community.entity;

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
@Table(name = "FAQ")
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FAQ_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @NotNull
    @Column(name = "question")
    private String question;

    @NotNull
    @Column(name = "answer")
    private String answer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "category")
    private String category;

    public void statusUpdate(Boolean status) {
        this.status = status;
    }
}
