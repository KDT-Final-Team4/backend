package com.example.salarying.Corporation.User.controller;

import com.example.salarying.Corporation.User.dto.MemberDTO;
import com.example.salarying.Corporation.User.entity.Member;
import com.example.salarying.Corporation.User.service.MemberService;
import com.example.salarying.global.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private MemberService memberService;

    @Operation(summary = "회원가입 API", description = "회사 관련 모든 정보 받아오기")
    @PostMapping("/signUp")
    public ResponseDTO<?> singUp(MemberDTO.SignUpRequest request){
        memberService.signUp(request);
        return new ResponseDTO<>().ok(null, "회원가입 성공");
    }
}
