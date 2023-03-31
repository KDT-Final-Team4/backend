package com.example.salarying.Corporation.Recruiting.controller;

import com.example.salarying.Corporation.Recruiting.dto.ProgressDTO;
import com.example.salarying.Corporation.Recruiting.service.ProgressService;
import com.example.salarying.global.dto.ResponseDTO;
import com.example.salarying.global.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor

@RestController
public class ProgressController {

    private final ProgressService progressService;


    /**
     * 채용공고별 전형단계 출력
     * @param customUserDetails: 로그인 기업회원
     * @param id: 채용공고 id
     * @return 채용공고별 전형단계
     */
    @Operation(summary = "채용공고별 전형단계 출력",description = "채용공고별 전형단계 출력 FOR USER")
    @PostMapping("/recruiting/progress/{id}")
    public ResponseDTO<?> showRecruitingProgress(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long id){
        ProgressDTO.ProgressResponse progressResponse = progressService.findProgressRecruitingIdAndUserId(customUserDetails.getUserId(),id);
        return new ResponseDTO<>().ok(progressResponse,"정상출력 데이터");
    }

}
