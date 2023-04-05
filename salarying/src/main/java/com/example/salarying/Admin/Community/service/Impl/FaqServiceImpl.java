package com.example.salarying.Admin.Community.service.Impl;

import com.example.salarying.Admin.Community.dto.FaqDTO;
import com.example.salarying.Admin.Community.entity.FAQ;
import com.example.salarying.Admin.Community.exception.CommunityException;
import com.example.salarying.Admin.Community.exception.CommunityExceptionType;
import com.example.salarying.Admin.Community.repository.FaqRepository;
import com.example.salarying.Admin.Community.service.FaqService;
import com.example.salarying.Admin.User.entity.Admin;
import com.example.salarying.Admin.User.service.AdminService;
import com.example.salarying.global.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;
    private final AdminService adminService;

    /**
     * FAQ 등록
     * @param adminId : 관리자 id
     * @param request : 등록할 FAQ DTO
     */
    @Override
    public void insertFaq(Long adminId, FaqDTO.InsertFaqRequest request) {
        Admin admin = adminService.findAdminById(adminId);
        FAQ faq = request.toEntity(admin);
        if (checkRequestDTO(request.getQuestion(), request.getAnswer(), request.getCategory())) {
            faqRepository.save(faq);
        }
    }

    /**
     * FAQ 목록 조회
     *
     * @return FAQ 목록
     */
    @Override
    public List<FaqDTO.FAQListResponse> faqList(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<FAQ> faqList = faqRepository.findAll();
        if (customUserDetails.getRole().equals("ADMIN") || customUserDetails.getRole().equals("SUPERADMIN")) {
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
        adminService.findAdminById(adminId);
        FAQ faq = findFaqId(request.getId());
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
        FAQ faq = findFaqId(id);
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
        adminService.findAdminById(adminId);
        FAQ faq = findFaqId(request.getId());
        faq.updateFaq(
                request.getQuestion(),
                request.getAnswer(),
                request.getCategory()
        );
        if (checkRequestDTO(request.getQuestion(), request.getAnswer(), request.getCategory())) {
            faqRepository.save(faq);
        }
    }

    /**
     * FAQ 삭제하는 함수
     * @param adminId : 관리자 id
     * @param faqId   : FAQ Id
     */
    @Override
    @Transactional
    public void deleteFaq(Long adminId, Long faqId) {
        adminService.findAdminById(adminId);
        FAQ faq = findFaqId(faqId);
        faqRepository.delete(faq);
    }
    /**
     * FAQ Id로 약관 찾는 함수
     * @param faqId : FAQ Id
     * @return : 해당 Id를 가진 약관
     */
    @Override
    public FAQ findFaqId(Long faqId) {
        Optional<FAQ> faq = faqRepository.findById(faqId);
        if (faq.isPresent()) return faq.get();
        else throw new CommunityException(CommunityExceptionType.NOT_EXIST);
    }

    /**
     * DTO 체크
     * @param question : FAQ 질문
     * @param answer : FAQ 답변
     * @param category : FAQ 카테고리
     * @return :
     */
    public Boolean checkRequestDTO(String question, String answer, String category) {
        if (question == null || question.equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_QUESTION);
        } else if (answer == null || answer.equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_ANSWER);
        } else if (category== null || category.equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_CATEGORY);
        } else {
            return true;
        }
    }
}
