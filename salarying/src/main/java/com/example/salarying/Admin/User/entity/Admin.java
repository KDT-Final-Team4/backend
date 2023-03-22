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

    @Column(name="name")
    private String adminName;

    @Column(name="type")
    private String adminType;

    @NotNull
    @Column(name="password")
    private String adminPassword;

    @NotNull
    @Column(name="email")
    private String adminEmail;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSignIn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @Column(name="admin_role")
    private String role;

}
