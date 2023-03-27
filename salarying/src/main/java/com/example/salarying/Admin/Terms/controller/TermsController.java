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

    @Operation(summary = "서비스 이용 약관 등록 API", description = "새로운 서비스 이용 약관 생성")
    @PostMapping("/terms")
    public ResponseDTO<?> AddTerms(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody TermsDTO.AddTermRequest request){
        termsService.insertTerm(userDetails.getUserId(), request);
        return new ResponseDTO<>().ok(null, "약관 등록 완료");

    }
}
