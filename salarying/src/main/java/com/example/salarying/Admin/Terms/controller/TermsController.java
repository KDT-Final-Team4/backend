package com.example.salarying.Admin.Terms.controller;

import com.example.salarying.Admin.Terms.dto.TermsDTO;
import com.example.salarying.Admin.Terms.service.TermsService;
import com.example.salarying.global.dto.ResponseDTO;
import com.example.salarying.global.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TermsController {

    private final TermsService termsService;

    /**
     * 약관 등록 API
     * @param userDetails : 작성자 이메일
     * @param request : 제목, 내용, 버전, 타입
     * @return : 약관 등록 성공 여부
     */
    @Operation(summary = "약관 등록 API", description = "새로운 약관 생성")
    @PostMapping("/terms")
    public ResponseDTO<?> AddTerms(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody TermsDTO.AddTermRequest request){
        termsService.insertTerm(userDetails.getUserId(), request);
        return new ResponseDTO<>().ok(null, "약관 등록 완료");

    }

    @Operation(summary = "약관 리스트 출력 API", description = "약관별 리스트 출력")
    @GetMapping("/terms")
    public ResponseDTO<?> ListTerms(@RequestParam String type){
        return new ResponseDTO<>().ok(termsService.termsList(type), "리스트 출력 완료");

    }
}
