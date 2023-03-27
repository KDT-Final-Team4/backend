package com.example.salarying.Admin.Terms.service;

import com.example.salarying.Admin.Terms.dto.TermsDTO;

public interface TermsService {
    String insertTerm(Long Id, TermsDTO.AddTermRequest request);
}
