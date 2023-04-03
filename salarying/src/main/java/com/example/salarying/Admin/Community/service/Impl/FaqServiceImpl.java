package com.example.salarying.Admin.Community.service.Impl;

import com.example.salarying.Admin.Community.dto.FaqDTO;
import com.example.salarying.Admin.Community.entity.FAQ;
import com.example.salarying.Admin.Community.exception.CommunityException;
import com.example.salarying.Admin.Community.exception.CommunityExceptionType;
import com.example.salarying.Admin.Community.repository.FaqRepository;
import com.example.salarying.Admin.Community.service.FaqService;
import com.example.salarying.Admin.User.entity.Admin;
import com.example.salarying.Admin.User.repository.AdminRepository;
import com.example.salarying.Corporation.User.exception.UserException;
import com.example.salarying.Corporation.User.exception.UserExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;
    private final AdminRepository adminRepository;

    /**
     * FAQ 등록
     * @param adminId : 관리자 id
     * @param request : 등록할 FAQ DTO
     */
    @Override
    public void insertFaq(Long adminId, FaqDTO.InsertFaqRequest request) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new UserException(UserExceptionType.NOT_LOGGED_IN));
        FAQ faq = request.toEntity(admin);
        if (checkRequestDTO(request)) {
            faqRepository.save(faq);
        }
    }

    /**
     * FAQ 목록 조회
     *
     * @return FAQ 목록
     */
    @Override
    public List<FaqDTO.FAQListResponse> faqList(Long adminId) {
        List<FAQ> faqList = faqRepository.findAll();
        Optional<Admin> admin = adminRepository.findById(adminId);
        if (admin.isPresent()) {
            return faqList.stream()
                    .map(FaqDTO.FAQListResponse::new)
                    .collect(Collectors.toList());
        } else {
            return faqList.stream()
                    .map(FaqDTO.FAQListResponse::new)
                    .filter(FaqDTO.FAQListResponse::getStatus)
                    .collect(Collectors.toList());
        }
    }

    /**
     * FAQ 상태 변경
     * @param adminId : 관리자 id
     * @param request : FAQ 상태 수정
     */
    @Override
    public void changeStatus(Long adminId, FaqDTO.FaqStatusRequest request) {
        adminRepository.findById(adminId).orElseThrow(() -> new UserException(UserExceptionType.NOT_LOGGED_IN));
        FAQ faq = faqRepository.findById(request.getId()).orElseThrow(() -> new CommunityException(CommunityExceptionType.NOT_EXIST));
        faq.statusUpdate(request.getStatus());
        faqRepository.save(faq);
    }

    /**
     * FAQ 상세 내용 가져오는 함수
     * @param id : FAQ Id
     * @return : FAQ 상세 내용 (질문, 답변, 작성자, 작성날짜)
     */
    @Override
    public FaqDTO.DetailResponse faqDetail(Long id) {
        FAQ faq = faqRepository.findById(id).orElseThrow(() -> new CommunityException(CommunityExceptionType.NOT_EXIST));
        return new FaqDTO.DetailResponse(faq);
    }

    /**
     * FAQ 수정 하는 함수
     * @param adminId : 관리자 id
     * @param request : 변경된 FAQ
     */
    @Override
    @Transactional
    public void updateFaq(Long adminId, FaqDTO.UpdateFaqRequest request) {
        adminRepository.findById(adminId).orElseThrow(() -> new UserException(UserExceptionType.NOT_LOGGED_IN));
        FAQ faq = faqRepository.findById(request.getId()).orElseThrow(() -> new CommunityException(CommunityExceptionType.NOT_EXIST));
        faq.updateFaq(
                request.getQuestion(),
                request.getAnswer()
        );
        if (request.getQuestion() == null || request.getQuestion().equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_QUESTION);
        } else if (request.getAnswer() == null || request.getAnswer().equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_ANSWER);
        } else {
            faqRepository.save(faq);
        }
    }

    /**
     * FAQ 삭제하는 함수
     * @param adminId : 관리자 id
     * @param FaqId   : FAQ Id
     */
    @Override
    @Transactional
    public void deleteFaq(Long adminId, Long FaqId) {
        adminRepository.findById(adminId).orElseThrow(() -> new UserException(UserExceptionType.NOT_LOGGED_IN));
        FAQ faq = faqRepository.findById(FaqId).orElseThrow(() -> new CommunityException(CommunityExceptionType.NOT_EXIST));
        faqRepository.delete(faq);
    }

    /**
     * DTO 형식 체크 메서드
     * @param request : 등록 하고자 하는 FAQ 정보 DTO
     * @return : FAQ 질문,답변,카테고리 없으면 false / 있으면 true
     */
    public Boolean checkRequestDTO(FaqDTO.InsertFaqRequest request) {
        if (request.getQuestion() == null || request.getQuestion().equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_QUESTION);
        } else if (request.getAnswer() == null || request.getAnswer().equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_ANSWER);
        } else if (request.getCategory() == null || request.getCategory().equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_CATEGORY);
        } else {
            return true;
        }
    }
}
