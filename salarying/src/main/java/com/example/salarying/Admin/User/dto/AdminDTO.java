package com.example.salarying.Admin.User.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AdminDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequest{
        private String email;
        private String password;
    }
}
