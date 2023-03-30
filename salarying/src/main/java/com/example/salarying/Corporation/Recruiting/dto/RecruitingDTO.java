package com.example.salarying.Corporation.Recruiting.dto;


import com.example.salarying.Corporation.Recruiting.entity.Progress;
import com.example.salarying.Corporation.Recruiting.entity.Recruiting;
import com.example.salarying.Corporation.User.entity.Member;
import lombok.*;

import java.util.Date;

public class RecruitingDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RecruitingResponse {


        private Long id;

        private String title;

        private Date postDate;

        private String task;

        private String status;

        public RecruitingResponse(Recruiting recruiting) {
            this.id = recruiting.getId();
            this.title = recruiting.getTitle();
            this.postDate = recruiting.getPostDate();
            this.task = recruiting.getTask();
            this.status = recruiting.getStatus();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RecruitingRequest {


        private String title;

        private String task;

        private Boolean document;

        private Boolean firstRound;

        private Boolean secondRound;

        private Boolean finalRound;


        public Recruiting toRecruitingEntity(Member member) {
            return Recruiting.builder()
                    .member(member)
                    .title(title)
                    .postDate(new Date())
                    .task(task)
                    .status("서류심사").build();
        }

        public Progress toProgressEntity(Recruiting recruiting) {
            return Progress.builder()
                    .recruiting(recruiting)
                    .hasDocument(document)
                    .hasFirstRound(firstRound)
                    .hasSecondRound(secondRound)
                    .hasFinalRound(finalRound)
                    .build();
        }

        }
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StatusRequest {


        private Long recruitingId;

        private String status;


    }

    }



