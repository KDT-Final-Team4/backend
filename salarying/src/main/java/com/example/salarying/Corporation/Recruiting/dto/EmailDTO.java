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

        private Long recruitingId;

        private String applicantEmail;

        private String title;

        private String content;

        private String progress;

        private String status;


    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "이메일 응답 DTO",description = "이메일 전송 내역 출력")
    public static class EmailResponse {

        private String recruitingName;

        private String applicantEmail;

        private Date sendDate;

        private String progress;

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
