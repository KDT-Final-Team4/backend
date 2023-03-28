package com.example.salarying.Corporation.Recruiting.controller;

import com.example.salarying.Corporation.Recruiting.dto.EmailDTO;
import com.example.salarying.Corporation.Recruiting.service.EmailService;
import com.example.salarying.global.dto.ResponseDTO;
import com.example.salarying.global.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PostMapping("/users/applicants/message")
    public ResponseDTO<?> sendEmail(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody List<EmailDTO.EmailRequest> requestList){
        emailService.sendAndSaveEmail(customUserDetails.getUserId(),requestList);
        return new ResponseDTO<>().ok("발송완료","메일이 발송되었습니다.");
    }
}
