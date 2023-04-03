package com.example.salarying.Corporation.User.controller;

import com.example.salarying.Admin.User.dto.AdminDTO;
import com.example.salarying.Corporation.User.dto.MemberDTO;
import com.example.salarying.Corporation.User.service.MemberService;
import com.example.salarying.global.dto.ResponseDTO;
import com.example.salarying.global.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;

    /**
     * 비밀번호 변경 전 사용자 확인을 위한 비밀번호 재확인 API
     * @param customUserDetails : 사용자를 찾기 위한 ID
     * @param request : 비밀번호
     * @return : 비밀번호 일치 여부
     */
    @Operation(summary = "비밀번호 확인 API", description = "비밀번호 변경 전 사용자 확인을 위한 비밀번호 재입력 FOR USER")
    @PostMapping("/users/password")
    public ResponseDTO<?> checkPassword(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody MemberDTO.CheckRequest request) {
        return new ResponseDTO<>().ok(null, memberService.checkPassword(customUserDetails.getUserId(), request));
    }

    /**
     * 기업회원 비밀번호 변경 API
     * @param customUserDetails : 회원 정보를 찾기 위한 ID
     * @param request : 새로운 비밀번호
     * @return : 비밀번호 변경 완료 여부
     */
    @Operation(summary = "비밀번호 변경 API", description = "새로운 비밀번호 저장 FOR USER")
    @PutMapping("/users/password")
    public ResponseDTO<?> changePassword(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody MemberDTO.ChangeRequest request) {
        return new ResponseDTO<>().ok(null, memberService.changePassword(customUserDetails.getUserId(), request));
    }

    /**
     * 기업회원의 마이페이지 정보 출력 API
     * @param customUserDetails : 로그인한 유저 정보
     * @return : 기업회원 정보
     */
    @Operation(summary = "기업회원 정보 출력 API", description = "기업회원 마이페이지의 기업 정보 출력 FOR USER")
    @GetMapping("/users/me")
    public ResponseDTO<?> showMyPage(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        return new ResponseDTO<>().ok(memberService.showMyPage(customUserDetails.getUserId()), "출력 완료");
    }

    /**
     * 기업회원 마이페이지의 정보 변경 API
     * @param customUserDetails : 로그인한 유저 정보
     * @param request : 변경 요청 DTO
     * @return : 변경 완료
     */
    @Operation(summary = "기업회원 정보 변경 API", description = "기업회원 마이페이지의 기업 정보 변경 FOR USER")
    @PutMapping("/users/me")
    public ResponseDTO<?> updateMyPage(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody MemberDTO.updateRequest request){
        return new ResponseDTO<>().ok(null, memberService.updateMyPage(customUserDetails.getUserId(), request));
    }


}
