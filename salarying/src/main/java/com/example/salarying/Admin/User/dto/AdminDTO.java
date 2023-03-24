package com.example.salarying.Admin.User.dto;

import com.example.salarying.Admin.User.entity.Admin;
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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "관리자 로그인 응답 DTO", description = "관리자 로그인시 출력되는 데이터")
    public static class LoginResponse{
        private String token;
        private String role;

        public LoginResponse(String token, Admin admin){
            this.token = token;
            this.role = admin.getRole();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "비밀번호 확인 DTO")
    public static class CheckRequest{
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "새로운 비밀번호 DTO", description = "비밀번호 변경을 위한 새로운 비밀번호")
    public static class ChangeRequest{
        private String password;
    }
}
