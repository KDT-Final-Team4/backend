package com.example.salarying.Corporation.Recruiting.service.impl;

import com.example.salarying.Corporation.Recruiting.dto.ProgressDTO;
import com.example.salarying.Corporation.Recruiting.entity.Progress;
import com.example.salarying.Corporation.Recruiting.exception.RecruitingException;
import com.example.salarying.Corporation.Recruiting.exception.RecruitingExceptionType;
import com.example.salarying.Corporation.Recruiting.repository.ProgressRepository;
import com.example.salarying.Corporation.Recruiting.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;

    /**
     * 채용공고 id와 기업 id로 채용전형 출력
     * @param userid: 기업 id
     * @param recruitingId: 채용공고 id
     * @return 해당 채용공고 전형단계
     */
    @Override
    public ProgressDTO.ProgressResponse findProgressRecruitingIdAndUserId(Long userid, Long recruitingId) {
        Progress progress = progressRepository.findProgressByRecruiting_IdAndRecruiting_Member_Id(recruitingId,userid)
                .orElseThrow(()->new RecruitingException(RecruitingExceptionType.NOT_EXIST));
        return new ProgressDTO.ProgressResponse(progress);
    }
}
