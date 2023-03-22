package com.example.salarying.Corporation.Recruiting.service.impl;


import com.example.salarying.Corporation.Recruiting.dto.ApplicantDTO;
import com.example.salarying.Corporation.Recruiting.repository.ApplicantRepository;
import com.example.salarying.Corporation.Recruiting.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    @Override
    public List<ApplicantDTO.ApplicantResponse> findApplicantByRecruitingId(Long user_id, Long recruiting_id) {
        List<ApplicantDTO.ApplicantResponse> applicantResponseList = applicantRepository.findApplicantByRecruitingId(recruiting_id)
                                                                                            .stream().filter(en->en.getRecruiting().getMember().getId()==user_id)
                                                                                                .map(entity->new ApplicantDTO.ApplicantResponse(entity))
                                                                                                .collect(Collectors.toList());
        return applicantResponseList;
    }
}
