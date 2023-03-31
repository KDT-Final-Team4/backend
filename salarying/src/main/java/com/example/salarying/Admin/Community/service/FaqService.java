package com.example.salarying.Admin.Community.service;

import com.example.salarying.Admin.Community.dto.FaqDTO;

import java.util.List;

public interface FaqService {
    void insertFaq(Long adminId, FaqDTO.InsertFaqRequest request);

    List<FaqDTO.FAQListResponse> faqList();

    void changeStatus(Long adminId, FaqDTO.FaqStatusRequest request);

    FaqDTO.DetailResponse faqDetail(Long id);

    void updateFaq(Long adminId, FaqDTO.UpdateFaqRequest request);
}
