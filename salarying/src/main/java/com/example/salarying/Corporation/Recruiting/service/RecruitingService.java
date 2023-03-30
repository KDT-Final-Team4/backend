package com.example.salarying.Corporation.Recruiting.service;


import com.example.salarying.Corporation.Recruiting.dto.RecruitingDTO;
import com.example.salarying.Corporation.Recruiting.entity.Recruiting;

import java.util.List;

public interface RecruitingService {

    List<RecruitingDTO.RecruitingResponse> findRecruitingByMemberId(Long userId);

    RecruitingDTO.RecruitingResponse insertRecruiting(Long userId,RecruitingDTO.RecruitingRequest request);

    Boolean checkRecruitingDTO(RecruitingDTO.RecruitingRequest request);

    Recruiting findById(Long recruitingId);

    RecruitingDTO.RecruitingResponse updateStatus(Long userId, RecruitingDTO.StatusRequest request);

    Boolean checkStatusDTO(RecruitingDTO.StatusRequest request);
}
