package com.example.salarying.Corporation.Recruiting.service;

import com.example.salarying.Corporation.Recruiting.dto.ApplicantDTO;

import java.util.List;

public interface ApplicantService {

    List<ApplicantDTO.ApplicantResponse> findApplicantByRecruitingId(Long recruiting_id);
}
