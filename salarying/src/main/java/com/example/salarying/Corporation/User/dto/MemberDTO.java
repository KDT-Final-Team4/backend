package com.example.salarying.Corporation.User.dto;

import com.example.salarying.Corporation.User.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignUpRequest{
        private String email;
        private String password;
        private String companyNm;
        private String companyTel;

        public Member toEntity(){
            return Member.builder()
                    .userEmail(this.email)
                    .userPw(this.password)
                    .companyNm(this.companyNm)
                    .companyTel(this.companyTel)
                    .role("USER")
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequest{
        private String email;
        private String password;
    }


}
