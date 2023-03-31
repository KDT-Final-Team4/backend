package com.example.salarying.Corporation.Recruiting.service;

import com.example.salarying.Corporation.Recruiting.dto.ApplicantDTO;

import java.util.List;

public interface ApplicantService {

    List<ApplicantDTO.ApplicantResponse> findApplicantByRecruitingId(Long userId,Long recruitingId);

    List<ApplicantDTO.ApplicantResponse> findApplicantByRecruitingIdAndProgressAndStatus(Long userId, ApplicantDTO.SelectionRequest request);

    ApplicantDTO.ApplicantResponse insertApplicant(Long userId,ApplicantDTO.ApplicantRequest request);

    Boolean checkApplicantRequestDTO(ApplicantDTO.ApplicantRequest request);

    ApplicantDTO.ApplicantResponse updateApplicant(Long userId,ApplicantDTO.ResultRequest request);

    Boolean checkResultRequestDTO(ApplicantDTO.ResultRequest request);

}
