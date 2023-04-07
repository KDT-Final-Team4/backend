package com.example.salarying.Corporation.Recruiting.dto;


import com.example.salarying.Corporation.Recruiting.entity.Applicant;
import com.example.salarying.Corporation.Recruiting.entity.Recruiting;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class ApplicantDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "지원자 정보 응답 DTO", description = "지원자 정보 출력")
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
    @Schema(name = "지원자 선택하기 위한 조건 요청 DTO", description = "지원자의 progress,status에 따른 지원자 정보 출력 ")
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
    @Schema(name = "지원자 요청 DTO", description = "지원자 정보 등록")
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
                    .status("불합격")
                    .build();
        }
    }
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "지원자 progress,status변경을 위한 DTO",description = "지원자 상태 변경")
    public static class ResultRequest {


        private Long recruitingId;

        private String email;

        private String progress;

        private String status;



    }
}
