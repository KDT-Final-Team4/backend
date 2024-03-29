package com.example.salarying.Corporation.User.service;

import com.example.salarying.Corporation.User.dto.MemberDTO;
import com.example.salarying.Corporation.User.entity.Member;

public interface MemberService {
    Member signUp(MemberDTO.SignUpRequest request);

    MemberDTO.LoginResponse login(MemberDTO.LoginRequest request);

    String checkPassword(Long Id, MemberDTO.CheckRequest request);

    String changePassword(Long Id, MemberDTO.ChangeRequest request);

    MemberDTO.MyPageResponse showMyPage(Long Id);

    String updateMyPage(Long Id, MemberDTO.updateRequest request);
}
