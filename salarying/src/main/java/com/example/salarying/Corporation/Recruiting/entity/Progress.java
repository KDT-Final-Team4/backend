package com.example.salarying.Corporation.Recruiting.entity;

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
@Table(name = "progress")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="progress_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiting_id")
    private Recruiting recruiting;

    @Column(name="document")
    private Boolean document;

    @Column(name="first_round")
    private Boolean firstRound;

    @Column(name="second_round")
    private Boolean secondRound;

    @Column(name="final")
    private Boolean finalRound;
}
