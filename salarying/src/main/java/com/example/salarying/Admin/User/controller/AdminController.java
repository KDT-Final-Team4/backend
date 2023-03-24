package com.example.salarying.Admin.User.controller;

import com.example.salarying.Admin.User.dto.AdminDTO;
import com.example.salarying.Admin.User.service.AdminService;
import com.example.salarying.global.dto.ResponseDTO;
import com.example.salarying.global.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;

    /**
     * 비밀번호 변경 전 사용자 확인을 위한 비밀번호 재확인 API
     * @param customUserDetails : 사용자를 찾기 위한 ID
     * @param request : 비밀번호
     * @return : 비밀번호 일치 여부
     */
    @Operation(summary = "비밀번호 확인 API", description = "비밀번호 변경 전 사용자 확인을 위한 비밀번호 재입력")
    @PostMapping("/admin/password")
    public ResponseDTO<?> checkPassword(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody AdminDTO.CheckRequest request) {
        return new ResponseDTO<>().ok(null, adminService.checkPassword(customUserDetails.getUserId(), request));
    }
}
