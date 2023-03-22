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

    @Column(name="has_document")
    private Boolean hasDocument;

    @Column(name="has_first_round")
    private Boolean hasFirstRound;

    @Column(name="has_second_round")
    private Boolean hasSecondRound;

    @Column(name="has_final")
    private Boolean hasFinalRound;
}
