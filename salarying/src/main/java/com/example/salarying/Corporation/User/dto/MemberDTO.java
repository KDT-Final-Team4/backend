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
        @Schema(name = "name", example = "김나나")
        private String name;
        @Schema(name = "position", example = "대표")
        private String position;

        public Member toEntity(){
            return Member.builder()
                    .email(this.email)
                    .password(this.password)
                    .companyName(this.companyName)
                    .companyPhoneNumber(this.companyPhoneNumber)
                    .name(this.name)
                    .position(this.position)
                    .lastModified(new Date())
                    .role("USER")
                    .status("일반")
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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MyPageResponse{
        private String companyName;
        private String companyPhoneNumber;
        private String email;
        private String name;
        private String position;
        private Date lastSignIn;
        private Date lastModified;
        private String status;

        public MyPageResponse(Member member) {
            this.companyName = member.getCompanyName();
            this.companyPhoneNumber = member.getCompanyPhoneNumber();
            this.email = member.getEmail();
            this.name = member.getName();
            this.position = member.getPosition();
            this.lastSignIn = member.getLastSignIn();
            this.lastModified = member.getLastModified();
            this.status = member.getStatus();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updateRequest{
        private String companyName;
        private String companyPhoneNumber;
        private String email;
        private String name;
        private String position;
    }


}
