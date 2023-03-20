package com.example.salarying.Corporation.User.entity;

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
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="employee_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @NotNull
    @Column(name="employee_name")
    private String employeeNm;

    @NotNull
    @Column(name="employee_tel")
    private String employeeTel;

    @NotNull
    @Column(name="employee_email")
    private String employeeEmail;

    @Column(name="status")
    private String status;
}
