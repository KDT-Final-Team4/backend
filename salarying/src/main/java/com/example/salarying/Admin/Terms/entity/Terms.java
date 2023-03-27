package com.example.salarying.Admin.Terms.entity;



import com.example.salarying.Admin.User.entity.Admin;
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
@Table(name = "agreement")
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="agreement_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(name="type")
    private String type;

    @Column(name="title")
    private String agreementTitle;

    @Column(name="content", columnDefinition = "TEXT")
    private String agreementContent;

    @Column(name="version")
    private Float version;

    @Column(name="status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}
