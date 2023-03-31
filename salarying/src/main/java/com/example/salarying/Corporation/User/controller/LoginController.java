package com.example.salarying.Corporation.User.controller;

import com.example.salarying.Corporation.User.dto.MemberDTO;
import com.example.salarying.Corporation.User.service.MemberService;
import com.example.salarying.global.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private MemberService memberService;

    /**
     *
     * @param request : 이메일, 비밀번호, 회사명, 회사번호를 가진 DTO
     * @return 회원가입 성공 시 HttpsStatus를 200번으로 반환, 이외의 예외들은 각각의 메세지와 함께 400번 반환
     */
    @Operation(summary = "회원가입 API", description = "회사 관련 모든 정보 받아오기 FOR USER")
    @PostMapping("/signup")
    public ResponseDTO<?> singUp(@RequestBody MemberDTO.SignUpRequest request){
        memberService.signUp(request);
        return new ResponseDTO<>().ok(null, "회원가입 성공");
    }

    /**
     * 사용자 로그인 기능
     * @param request : 이메일, 비밀번호를 가진 DTO
     * @return 로그인 성공 시 access token 반환
     */
    @Operation(summary = "로그인 API", description = "이메일과 비밀번호로 로그인하기 FOR USER")
    @PostMapping("/users/login")
    public ResponseDTO<?> login(@RequestBody MemberDTO.LoginRequest request){
        return new ResponseDTO<>().ok(memberService.login(request), "로그인 성공");
    }

}
