package com.example.salarying.Admin.User.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Admin_Pw")
public class AdminPw {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="adminPW_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="admin_id")
    private Admin admin;

    @Column(name = "admin_pw1")
    private String firstPw;

    @Column(name = "pw1_date")
    private String firstPwDate;

    @Column(name = "admin_pw2")
    private String secondPw;

    @Column(name = "pw2_date")
    private String secondPwDate;

    @Column(name = "admin_pw3")
    private String thirdPw;

    @Column(name = "pw3_date")
    private String  thirdPwDate;
}
