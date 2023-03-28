package com.example.salarying.Corporation.Recruiting.service.impl;

import com.example.salarying.Corporation.Recruiting.dto.EmailDTO;
import com.example.salarying.Corporation.Recruiting.entity.Applicant;
import com.example.salarying.Corporation.Recruiting.entity.Email;
import com.example.salarying.Corporation.Recruiting.entity.Recruiting;
import com.example.salarying.Corporation.Recruiting.exception.EmailException;
import com.example.salarying.Corporation.Recruiting.exception.EmailExceptionType;
import com.example.salarying.Corporation.Recruiting.repository.ApplicantRepository;
import com.example.salarying.Corporation.Recruiting.repository.EmailRepository;
import com.example.salarying.Corporation.Recruiting.service.EmailService;
import com.example.salarying.Corporation.Recruiting.service.RecruitingService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {


    private final EmailRepository emailRepository;

    private final JavaMailSender javaMailSender;

    private final ApplicantRepository applicantRepository;

    private final RecruitingService recruitingService;


    /**
     * 이메일 발송 및 내용 저장
     * @param userId: 기업회원 id
     * @param requestList: 이메일요청 DTO 리스트
     */
    @Transactional
    public void sendAndSaveEmail(Long userId, List<EmailDTO.EmailRequest> requestList) {

        for (EmailDTO.EmailRequest request : requestList) {
            Recruiting recruiting = recruitingService.findById(request.getRecruitingId());
            Optional<Applicant> applicant = applicantRepository.findApplicantByApplicantEmailAndRecruitingAndProgressAndStatus(request.getApplicantEmail(),recruiting, request.getProgress(), request.getStatus());

            if (checkEmailDTO(request)&&recruiting.getMember().getId().equals(userId)) {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setSubject(request.getTitle());
                mailMessage.setText(request.getContent());
                mailMessage.setFrom(recruiting.getMember().getEmail());
                mailMessage.setTo(applicant.get().getApplicantEmail());
                javaMailSender.send(mailMessage);
                Email email = Email.builder().applicant(applicant.get()).recruiting(recruiting).progress(request.getProgress())
                        .sendDate(new Date()).status(request.getStatus()).build();
                emailRepository.save(email);
            }else{
                throw new EmailException(EmailExceptionType.NOT_EXIST_APPLICANT);
            }

        }


    }

    /**
     * 이메일 요청 DTO 형식 체크
     * @param request: 이메일 요청 DTO
     * @return: 올바른 형식-true/ 아니면 예외처리
     */
    public Boolean checkEmailDTO(EmailDTO.EmailRequest request) {
        if (request.getTitle() == null || request.getTitle().equals("")) {
            throw new EmailException(EmailExceptionType.NOT_EXIST_SUBJECT);
        } else if (request.getContent() == null || request.getContent().equals("")) {
            throw new EmailException(EmailExceptionType.NOT_EXIST_CONTENT);
        } else if (request.getApplicantEmail().isEmpty()) {
            throw new EmailException(EmailExceptionType.NOT_EXIST_EMAIL);
        } else if (!request.getApplicantEmail().matches("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            throw new EmailException(EmailExceptionType.NOT_EMAIL_FORMAT);
        } else {
            return true;
        }
    }
}



