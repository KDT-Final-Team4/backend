package com.example.salarying.Corporation.Recruiting.service;


import com.example.salarying.Corporation.Recruiting.dto.RecruitingDTO;

import java.util.List;

public interface RecruitingService {

    List<RecruitingDTO.RecruitingResponse> findRecruitingByMemberId(Long userId);

    RecruitingDTO.RecruitingResponse insertRecruiting(Long userId,RecruitingDTO.RecruitingRequest request);
}
