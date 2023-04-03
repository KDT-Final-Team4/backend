package com.example.salarying.Corporation.Recruiting.dto;


import com.example.salarying.Corporation.Recruiting.entity.Progress;
import com.example.salarying.Corporation.Recruiting.entity.Recruiting;
import com.example.salarying.Corporation.User.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

public class RecruitingDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "채용공고 응답 DTO",description = "채용공고 정보 출력")
    public static class RecruitingResponse {

        @Schema(description = "채용공고 id")
        private Long id;
        @Schema(description = "채용공고 이름")
        private String title;
        @Schema(description = "채용공고 등록시간")
        private Date postDate;
        @Schema(description = "모집 직무")
        private String task;
        @Schema(description = "채용공고 진행단계",allowableValues = {"서류전형","1차전형","2차전형","최종전형"})
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
    @Schema(name = "채용공고 요청 DTO",description = "채용공고 등록")
    public static class RecruitingRequest {

        @Schema(description = "채용공고 이름")
        private String title;
        @Schema(description = "모집 직무")
        private String task;
        @Schema(description = "서류전형 여부",allowableValues = {"true","false"})
        private Boolean document;
        @Schema(description = "1차전형 여부",allowableValues = {"true","false"})
        private Boolean firstRound;
        @Schema(description = "2차전형 여부",allowableValues = {"true","false"})
        private Boolean secondRound;
        @Schema(description = "최종전형 여부",allowableValues = {"true","false"})
        private Boolean finalRound;


        public Recruiting toRecruitingEntity(Member member) {
            return Recruiting.builder()
                    .member(member)
                    .title(title)
                    .postDate(new Date())
                    .task(task)
                    .status("서류전형").build();
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
    @Schema(name = "전형 요청 DTO",description = "채용공고 전형단계 수정")
    public static class StatusRequest {

        @Schema(description = "채용공고 id")
        private Long recruitingId;
        @Schema(description = "채용공고 진행단계",allowableValues = {"서류전형","1차전형","2차전형","최종전형"})
        private String status;


    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "채용공고 상세 응답 DTO",description = "채용공고 상세 출력")
    public static class DetailResponse {
        @Schema(description = "채용공고 이름")
        private String title;
        @Schema(description = "채용공고 등록시간")
        private Date postDate;
        @Schema(description = "모집 직무")
        private String task;
        @Schema(description = "채용공고 진행단계",allowableValues = {"서류전형","1차전형","2차전형","최종전형"})
        private String status;

        public DetailResponse(Recruiting recruiting) {
            this.title = recruiting.getTitle();
            this.postDate = recruiting.getPostDate();
            this.task = recruiting.getTask();
            this.status = recruiting.getStatus();
        }
    }
    }



