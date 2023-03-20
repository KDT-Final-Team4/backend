package com.example.salarying.Admin.User.entity;

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
@Table(name = "Administrator")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="admin_id")
    private Long id;

    @Column(name="admin_name")
    private String adminNm;

    @Column(name="admin_type")
    private String adminType;

    @NotNull
    @Column(name="admin_pw")
    private String adminPw;

    @NotNull
    @Column(name="admin_email")
    private String adminEmail;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSignIn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;


}
