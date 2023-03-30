package com.example.salarying.Corporation.Recruiting.dto;


import com.example.salarying.Corporation.Recruiting.entity.Applicant;
import com.example.salarying.Corporation.Recruiting.entity.Recruiting;
import lombok.*;

public class ApplicantDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApplicantResponse{

        private String applicantNm;

        private String applicantTel;

        private String applicantEmail;

        private String progress;

        private String status;

        public ApplicantResponse(Applicant applicant){
            this.applicantNm = applicant.getApplicantName();
            this.applicantTel = applicant.getApplicantPhoneNumber();
            this.applicantEmail = applicant.getApplicantEmail();
            this.progress = applicant.getProgress();
            this.status = applicant.getStatus();
        }


    }
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SelectionRequest {


        private Long id;

        private String progress;

        private String status;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApplicantRequest {


        private Long recruitingId;

        private String email;

        private String name;

        private String number;

        public Applicant toEntity(Recruiting recruiting){
            return Applicant.builder()
                    .recruiting(recruiting)
                    .applicantName(name)
                    .applicantPhoneNumber(number)
                    .applicantEmail(email)
                    .progress("서류전형")
                    .build();
        }
    }
}
