package com.example.salarying.Admin.Community.service;

import com.example.salarying.Admin.Community.dto.FaqDTO;
import com.example.salarying.Admin.Community.entity.FAQ;
import com.example.salarying.global.jwt.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface FaqService {
    void insertFaq(Long adminId, FaqDTO.InsertFaqRequest request);

    List<FaqDTO.FAQListResponse> faqList(@AuthenticationPrincipal CustomUserDetails userDetails);

    void changeStatus(Long adminId, FaqDTO.FaqStatusRequest request);

    FaqDTO.DetailResponse faqDetail(Long id);

    void updateFaq(Long adminId, FaqDTO.UpdateFaqRequest request);

    void deleteFaq(Long adminId, Long faqId);

    FAQ findFaqId(Long faqId);
}
