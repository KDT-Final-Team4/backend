package com.example.salarying.Corporation.Recruiting.dto;

import lombok.*;


public class EmailDTO {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmailRequest {

        private Long recruitingId;

        private String applicantEmail;

        private String title;

        private String content;

        private String progress;

        private String status;


    }

}
