package com.example.salarying.Corporation.Recruiting.entity;

import com.sun.istack.NotNull;
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
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="applicant_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiting_id")
    private Recruiting recruiting;

    @NotNull
    @Column(name="applicant_name")
    private String applicantName;

    @NotNull
    @Column(name="applicant_phone_number")
    private String applicantPhoneNumber;

    @NotNull
    @Column(name="applicant_email")
    private String applicantEmail;

    @Column(name="progress")
    private String progress;

    @Column(name="status")
    private String status;
}
