package com.example.salarying.Admin.User.controller;

import com.example.salarying.Admin.User.dto.AdminDTO;
import com.example.salarying.Admin.User.service.AdminService;
import com.example.salarying.global.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdminLoginController {

    private AdminService adminService;

    /**
     * 관리자 로그인 API
     * @param request : 관리자 로그인 DTO
     * @return : access token
     */
    @Operation(summary = "관리자 로그인 API", description = "이메일과 비밀번호로 로그인하기")
    @PostMapping("/admin/login")
    public ResponseDTO<?> login(@RequestBody AdminDTO.LoginRequest request){
        return new ResponseDTO<>().ok(adminService.login(request), "로그인 성공");
    }
}
