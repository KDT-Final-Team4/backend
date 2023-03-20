package com.example.salarying.Admin.Agreement.entity;



import com.example.salarying.Admin.User.entity.Admin;
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
@Table(name = "agreement")
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="agreement_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(name="title")
    private String agreementTitle;

    @Column(name="content")
    private String agreementContent;

    @Column(name="version")
    private Float version;

    @Column(name="status")
    private String status;








}
