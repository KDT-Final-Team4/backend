package com.example.salarying.Admin.User.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AdminDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "관리자 로그인 DTO", description = "관리자 로그인시 입력받는 데이터")
    public static class LoginRequest{

        @Schema(name = "email", example = "admin@email.com")
        private String email;
        @Schema(name = "password", example = "admin@1234")
        private String password;
    }
}
