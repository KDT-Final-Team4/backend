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
        @Schema(name = "지원자 이름")
        private String applicantNm;
        @Schema(name = "지원자 연락처")
        private String applicantTel;
        @Schema(name = "지원자 이메일")
        private String applicantEmail;
        @Schema(name = "지원자 전형",allowableValues = {"서류전형","1차전형","2차전형","최종전형"})
        private String progress;
        @Schema(description = "합불여부",allowableValues = {"합격","불합격"})
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

        @Schema(description = "채용공고 id")
        private Long id;
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
    @Schema(name = "지원자 요청 DTO", description = "지원자 정보 등록")
    public static class ApplicantRequest {

        @Schema(name = "채용공고 id")
        private Long recruitingId;
        @Schema(name = "지원자 이메일")
        private String email;
        @Schema(name = "지원자 이름")
        private String name;
        @Schema(name = "지원자 연락처")
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

        @Schema(description = "채용공고 id")
        private Long recruitingId;
        @Schema(description = "지원자 이메일")
        private String email;
        @Schema(description = "채용전형",allowableValues = {"서류전형","1차전형","2차전형","최종전형"})
        private String progress;
        @Schema(description = "합불여부",allowableValues = {"합격","불합격"})
        private String status;



    }
}
