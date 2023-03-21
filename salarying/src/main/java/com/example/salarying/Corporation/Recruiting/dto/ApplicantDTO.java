package com.example.salarying.Corporation.Recruiting.dto;


import com.example.salarying.Corporation.Recruiting.entity.Applicant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
            this.applicantNm = applicant.getApplicantNm();
            this.applicantTel = applicant.getApplicantTel();
            this.applicantEmail = applicant.getApplicantEmail();
            this.progress = applicant.getProgress();
            this.status = applicant.getStatus();
        }


    }
}
