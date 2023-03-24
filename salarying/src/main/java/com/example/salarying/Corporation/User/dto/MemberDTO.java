package com.example.salarying.Corporation.User.dto;

import com.example.salarying.Corporation.User.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

public class MemberDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignUpRequest{
        @Schema(name = "email", example = "test@email.com")
        private String email;
        @Schema(name = "password", example = "test@1234")
        private String password;
        @Schema(name = "companyName", example = "test")
        private String companyName;
        @Schema(name = "companyPhoneNumber", example = "010-1234-1234")
        private String companyPhoneNumber;

        public Member toEntity(){
            return Member.builder()
                    .email(this.email)
                    .password(this.password)
                    .companyName(this.companyName)
                    .companyPhoneNumber(this.companyPhoneNumber)
                    .lastModified(new Date())
                    .role("USER")
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequest{
        @Schema(name = "email", example = "test@email.com")
        private String email;
        @Schema(name = "password", example = "test@1234")
        private String password;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginResponse{
        @Schema(name = "email", example = "test@email.com")
        private String token;
        @Schema(name = "password", example = "test@1234")
        private String role;

        public LoginResponse(String token, Member member){
            this.token = token;
            this.role = member.getRole();

        }

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CheckRequest{
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChangeRequest{
        private String password;
    }


}
