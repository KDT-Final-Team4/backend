package com.example.salarying.Corporation.Recruiting.dto;

import com.example.salarying.Corporation.Recruiting.entity.Email;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;


public class EmailDTO {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "이메일 요청 DTO", description = "이메일 발송")
    public static class EmailRequest {
        @Schema(description = "채용공고 id")
        private Long recruitingId;
        @Schema(description = "지원자 이메일")
        private String applicantEmail;
        @Schema(description = "메일 제목")
        private String title;
        @Schema(description = "메일 내용",example = "~님 (서류,1차,2차,최종)전형에서 (합격,불합격)하셨습니다.")
        private String content;
        @Schema(description = "채용전형",allowableValues = {"서류전형","1차전형","2차전형","최종전형"})
        private String progress;
        @Schema(description = "합불여부",allowableValues = {"합격","불합격"})
        private String status;


    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "이메일 응답 DTO",description = "이메일 전송 내역 출력")
    public static class EmailResponse {
        @Schema(description = "채용공고 이름")
        private String recruitingName;
        @Schema(description = "지원자 이메일")
        private String applicantEmail;
        @Schema(description = "발송 시간")
        private Date sendDate;
        @Schema(description = "채용전형",allowableValues = {"서류전형","1차전형","2차전형","최종전형"})
        private String progress;
        @Schema(description = "합불여부",allowableValues = {"합격","불합격"})
        private String status;

        public EmailResponse(Email email){
            this.recruitingName = email.getRecruiting().getTitle();
            this.applicantEmail = email.getApplicant().getApplicantEmail();
            this.sendDate = email.getSendDate();
            this.progress = email.getProgress();
            this.status = email.getStatus();
        }
    }

}
