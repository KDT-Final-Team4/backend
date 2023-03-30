package com.example.salarying.Corporation.Recruiting.service;

import com.example.salarying.Corporation.Recruiting.dto.ProgressDTO;

public interface ProgressService {

    ProgressDTO.ProgressResponse findProgressRecruitingIdAndUserId(Long userid, Long recruitingId);

}
