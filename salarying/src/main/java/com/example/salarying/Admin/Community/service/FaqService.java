package com.example.salarying.Admin.Community.service;

import com.example.salarying.Admin.Community.dto.FaqDTO;

public interface FaqService {
    void insertFaq(Long adminId, FaqDTO.InsertFaqRequest request);
}
