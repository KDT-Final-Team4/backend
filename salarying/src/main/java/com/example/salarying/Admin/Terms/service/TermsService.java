package com.example.salarying.Admin.Terms.service;

import com.example.salarying.Admin.Terms.dto.TermsDTO;

import java.util.List;

public interface TermsService {

    String insertTerm(Long Id, TermsDTO.AddTermRequest request);

    List<TermsDTO.TermsListResponse> termsList(String type);

    String changeStatus(TermsDTO.StatusRequest request);
}
