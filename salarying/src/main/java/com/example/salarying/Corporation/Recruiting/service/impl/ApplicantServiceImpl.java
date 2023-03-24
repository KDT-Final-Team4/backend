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

    /**
     * 기업id와 채용공고id가 일치하는 지원자리스트 출력
     * @param userId: 기업회원 id
     * @param recruitingId: 채용공고 id
     * @return: 기업의 채용공고별 지원자 리스트
     */
    @Override
    public List<ApplicantDTO.ApplicantResponse> findApplicantByRecruitingId(Long userId, Long recruitingId) {
        List<ApplicantDTO.ApplicantResponse> applicantResponseList = applicantRepository.findApplicantByRecruitingId(recruitingId)
                                                                                            .stream().filter(en->en.getRecruiting().getMember().getId()==userId)
                                                                                                .map(entity->new ApplicantDTO.ApplicantResponse(entity))
                                                                                                .collect(Collectors.toList());
        return applicantResponseList;
    }


    /**
     * 기업회원 id,채용공고id,progress,status가 일치하는 지원자리스트 출력
     * @param userId: 기업회원 id
     * @param request: 채용공고id,채용전형,합격여부 정보를 가진 DTO
     * @return 조건에 해당하는 지원자 리스트
     */
    @Override
    public List<ApplicantDTO.ApplicantResponse> findApplicantByRecruitingIdAndProgressAndStatus(Long userId,ApplicantDTO.SelectionRequest request) {
        List<ApplicantDTO.ApplicantResponse> applicantResponseList = findApplicantByRecruitingId(userId, request.getId())
                                                                    .stream()
                                                                    .filter(e->e.getStatus().equals(request.getStatus())&&e.getProgress().equals(request.getProgress()))
                                                                    .collect(Collectors.toList());

        return applicantResponseList;
    }
}
