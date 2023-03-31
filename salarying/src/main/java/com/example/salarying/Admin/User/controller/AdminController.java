package com.example.salarying.Admin.User.controller;

import com.example.salarying.Admin.User.dto.AdminDTO;
import com.example.salarying.Admin.User.service.AdminService;
import com.example.salarying.global.dto.ResponseDTO;
import com.example.salarying.global.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "비밀번호 확인 API", description = "비밀번호 변경 전 사용자 확인을 위한 비밀번호 재입력 FOR ADMIN, SUPERADMIN")
    @PostMapping("/admin/password")
    public ResponseDTO<?> checkPassword(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody AdminDTO.CheckRequest request) {
        return new ResponseDTO<>().ok(null, adminService.checkPassword(customUserDetails.getUserId(), request));
    }

    /**
     * 관리자 비밀번호 변경 API
     * @param customUserDetails : 관리자 정보를 찾기 위한 ID
     * @param request : 새로운 비밀번호
     * @return : 비밀번호 변경 완료 여부
     */
    @Operation(summary = "비밀번호 변경 API", description = "새로운 비밀번호 저장 FOR ADMIN, SUPERADMIN")
    @PutMapping("/admin/password")
    public ResponseDTO<?> changePassword(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody AdminDTO.ChangeRequest request) {
        return new ResponseDTO<>().ok(null, adminService.changePassword(customUserDetails.getUserId(), request));
    }

    /**
     * 관리자 별 담당 기업 리스트 확인 API
     * @param customUserDetails : 사용자 정보를 위한 userDetails
     * @return : 담당 기업 리스트
     */
    @Operation(summary = "관리자 별 담당 기업 리스트 출력", description = "관리자 담당 기업 리스트 FOR ADMIN, SUPERADMIN")
    @GetMapping("/corporations")
    public ResponseDTO<?> manageList(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return new ResponseDTO<>().ok(adminService.manageList(customUserDetails.getUserId()), "출력 완료");
    }
}
