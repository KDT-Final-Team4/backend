package com.example.salarying.Corporation.User.service;

import com.example.salarying.Corporation.User.dto.MemberDTO;
import com.example.salarying.Corporation.User.entity.Member;

public interface MemberService {
    Member signUp(MemberDTO.SignUpRequest request);

    Member findByUserByEmail(String email);
}
