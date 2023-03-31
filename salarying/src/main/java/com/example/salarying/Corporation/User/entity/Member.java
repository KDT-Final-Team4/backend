package com.example.salarying.Corporation.User.entity;

import com.example.salarying.Admin.User.entity.Admin;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    private String companyName;

    @NotNull
    @Column(name="company_phone_number")
    private String companyPhoneNumber;

    @NotNull
    @Column(name="email")
    private String email;

    @NotNull
    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="position")
    private String position;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSignIn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @Column(name="status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "member_role")
    private String role;

    @Builder
    public Member(String companyName, String companyPhoneNumber, String email, String password, String role) {
        this.companyName = companyName;
        this.companyPhoneNumber = companyPhoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void encodePassword(PasswordEncoder passwordEncoder, Admin admin) {
        password = passwordEncoder.encode(password);
        this.admin = admin;
        this.lastModified = new Date();
    }

    public void updatePassword(PasswordEncoder passwordEncoder, String newPassword) {
        this.password = passwordEncoder.encode(newPassword);
        this.lastModified = new Date();
    }

    public void updateLoginDate(){
        this.lastSignIn = new Date();
    }
}
