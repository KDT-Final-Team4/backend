package com.example.salarying.Admin.Terms.service;

import com.example.salarying.Admin.Terms.dto.TermsDTO;

import java.util.List;

public interface TermsService {

    String insertTerm(Long Id, TermsDTO.AddTermRequest request);

    List<TermsDTO.TermsListResponse> termsList(String type);

    String changeStatus(TermsDTO.StatusRequest request);

    TermsDTO.DetailResponse showDetail(Long Id);

    String updateTerm(Long adminId, TermsDTO.UpdateRequest request);

    String deleteTerm(Long userId, Long termId);
}
