package com.example.salarying.Corporation.Recruiting.controller;

import com.example.salarying.Corporation.Recruiting.dto.EmailDTO;
import com.example.salarying.Corporation.Recruiting.service.EmailService;
import com.example.salarying.global.dto.ResponseDTO;
import com.example.salarying.global.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EmailController {

    private final EmailService emailService;

    /**
     * 이메일 발송하는 API
     * @param customUserDetails: 로그인 기업회원 id
     * @param requestList: 이메일 요청 DTO 리스트
     * @return: "발송완료","메일이 발송되었습니다."
     */
    @Operation(summary = "이메일 전송",description = "이메일 전송 FOR USER")
    @PostMapping("/applicants/message")
    public ResponseDTO<?> sendEmail(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody List<EmailDTO.EmailRequest> requestList){
        emailService.sendAndSaveEmail(customUserDetails.getUserId(),requestList);
        return new ResponseDTO<>().ok("발송완료","메일이 발송되었습니다.");
    }

    /**
     * 기업회원 id에 따른 메일 전송 내역 출력 API
     * @param customUserDetails: 로그인 기업회원 id
     * @return: 메일 전송 내역
     */
    @Operation(summary = "메일 전송 내역 출력",description = "메일 전송 내역 출력 FOR USER")
    @GetMapping("/applicants/message")
    public ResponseDTO<?> showEmail(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        List<EmailDTO.EmailResponse> emailResponseList = emailService.findEmailByMemberId(customUserDetails.getUserId());
        return new ResponseDTO<>().ok(emailResponseList,"정상출력");
    }
}
