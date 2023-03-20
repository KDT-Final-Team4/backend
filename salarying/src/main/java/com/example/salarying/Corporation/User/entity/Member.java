package com.example.salarying.Corporation.User.entity;

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
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @NotNull
    @Column(name="company_name")
    private String companyNm;

    @NotNull
    @Column(name="company_tel")
    private String companyTel;

    @NotNull
    @Column(name="member_email")
    private String userEmail;

    @NotNull
    @Column(name="member_pw")
    private String userPw;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSignIn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @Column(name="status")
    private String status;

    @Column(name = "type")
    private String type;
}
