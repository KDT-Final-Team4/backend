package com.example.salarying.Corporation.Recruiting.service;

import com.example.salarying.Corporation.Recruiting.dto.EmailDTO;

import java.util.List;

public interface EmailService {
    void sendAndSaveEmail(Long userId, List<EmailDTO.EmailRequest> requestList);

    Boolean checkEmailDTO(EmailDTO.EmailRequest request);

    List<EmailDTO.EmailResponse> findEmailByMemberId(Long userId);

}
