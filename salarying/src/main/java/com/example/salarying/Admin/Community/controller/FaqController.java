package com.example.salarying.Admin.Community.controller;

import com.example.salarying.Admin.Community.dto.FaqDTO;
import com.example.salarying.Admin.Community.service.FaqService;
import com.example.salarying.global.dto.ResponseDTO;
import com.example.salarying.global.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FaqController {
    private final FaqService faqService;

    /**
     * FAQ 등록 API
     * @param userDetails : 로그인한 관리자
     * @param request     : 등록할 공지사항 정보 DTO
     * @return : FAQ 등록 성공 여부
     */
    @Operation(summary = "FAQ 등록", description = "새로운 FAQ 등록 FOR ADMIN, SUPERADMIN")
    @PostMapping("/faq")
    public ResponseDTO<?> insertFaq(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody FaqDTO.InsertFaqRequest request) {
        faqService.insertFaq(userDetails.getUserId(), request);
        return new ResponseDTO<>().ok(null, "FAQ 등록 완료");
    }

    /**
     * FAQ 목록 조회 API
     * @return : FAQ 전체 목록
     */
    @Operation(summary = "FAQ 리스트 출력", description = "FAQ 리스트 출력 FOR ADMIN, SUPERADMIN, USER")
    @GetMapping("/faq")
    public ResponseDTO<?> faqList() {
        List<FaqDTO.FAQListResponse> faqListResponses = faqService.faqList();
        return new ResponseDTO<>().ok(faqListResponses, "정상 출력");
    }

    /**
     * FAQ 상태 수정 API
     * @param userDetails : 로그인한 관리자
     * @param request     : 수정하는 FAQ 상태 DTO
     * @return : 수정 성공 메시지
     */
    @Operation(summary = "FAQ 상태 수정", description = "FAQ 공개 여부 상태 변경 FOR ADMIN, SUPERADMIN")
    @PutMapping("/faq/status")
    public ResponseDTO<?> changeStatus(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody FaqDTO.FaqStatusRequest request) {
        faqService.changeStatus(userDetails.getUserId(), request);
        return new ResponseDTO<>().ok(null, "수정 되었습니다.");
    }

    /**
     * FAQ 내용 상세 출력 API
     * @param id : FAQ Id
     * @return : FAQ 상세 내용 출력
     */
    @Operation(summary = "FAQ 상세정보 조회", description = "FAQ 상세 보기 FOR ADMIN, SUPERADMIN, USER")
    @GetMapping("/faq/{id}")
    public ResponseDTO<?> faqDetail(@PathVariable("id") Long id) {
        FaqDTO.DetailResponse response = faqService.faqDetail(id);
        return new ResponseDTO<>().ok(response, "정상 출력");
    }

    /**
     * FAQ 내용 수정 API
     * @param userDetails : 로그인한 관리자
     * @param request : 변경 요청 DTO
     * @return : 수정 완료
     */
    @Operation(summary = "FAQ 정보 수정", description = "FAQ 내용 수정 FOR ADMIN, SUPERADMIN")
    @PutMapping("/faq")
    public ResponseDTO<?> updateFaq(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody FaqDTO.UpdateFaqRequest request) {
        faqService.updateFaq(userDetails.getUserId(), request);
        return new ResponseDTO<>().ok(null, "수정 완료");
    }

    /**
     * FAQ 삭제 API
     * @param userDetails : 로그인한 관리자
     * @param request : 삭제할 FAQ DTO
     * @return : 삭제 완료
     */
    @Operation(summary = "FAQ 삭제", description = "FAQ 삭제 FOR ADMIN, SUPERADMIN")
    @DeleteMapping("/faq")
    public ResponseDTO<?> deleteFaq(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody FaqDTO.DeleteFaqRequest request) {
        faqService.deleteFaq(userDetails.getUserId(), request.getId());
        return new ResponseDTO<>().ok(null, "삭제 완료.");
    }
}
