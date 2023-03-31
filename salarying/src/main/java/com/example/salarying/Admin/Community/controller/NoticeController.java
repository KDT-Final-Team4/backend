package com.example.salarying.Admin.Community.controller;

import com.example.salarying.Admin.Community.dto.NoticeDTO;
import com.example.salarying.Admin.Community.service.NoticeService;
import com.example.salarying.global.dto.ResponseDTO;
import com.example.salarying.global.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 관리자 id로 공지사항 등록 API
     * @param userDetails : 로그인한 관리자
     * @param request     : 등록할 공지사항 정보 DTO
     * @return : 등록된 공지사항 DTO
     */
    @Operation(summary = "공지사항 등록")
    @PostMapping("/notice")
    public ResponseDTO<?> insertNotice(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody NoticeDTO.NoticeRequest request) {
        NoticeDTO.NoticeResponse responseDTO = noticeService.insertNotice(userDetails.getUserId(), request);
        return new ResponseDTO<>().ok(responseDTO, "공지사항 등록 완료");
    }

    /**
     * 공지사항 목록 조회 API
     * @return : 공지사항 전체 목록
     */
    @Operation(summary = "공지사항 리스트 조회")
    @GetMapping("/notice")
    public ResponseDTO<?> noticeList() {
        List<NoticeDTO.NoticeList> noticeResponse = noticeService.noticeList();
        return new ResponseDTO<>().ok(noticeResponse, "정상 출력");
    }

    /**
     * 공지사항 정보 조회 API
     * @param id : 공지사항 id
     * @return : 공지사항 id를 포함한 공지사항 상세 정보 + "정상 출력"
     */
    @Operation(summary = "공지사항 정보 조회")
    @GetMapping("/notice/{id}")
    public ResponseDTO<?> noticeDetail(@PathVariable("id") Long id) {
        NoticeDTO.NoticeResponse response = noticeService.noticeDetail(id);
        return new ResponseDTO<>().ok(response, "정상출력");
    }

    /**
     * 공지사항 삭제 API
     * @param userDetails : 로그인한 관리자
     * @param request     : 삭제하고자 하는 공지사항 ID DTO
     * @return : 삭제 성공 메시지
     */
    @Operation(summary = "공지사항 삭제")
    @DeleteMapping("/notice")
    public ResponseDTO<?> deleteNotice(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody NoticeDTO.DeleteRequest request) {
        noticeService.deleteNotice(userDetails.getUserId(), request.getId());
        return new ResponseDTO<>().ok(null, "삭제 되었습니다.");
    }

    /**
     * 공지사항 수정 API
     * @param userDetails : 로그인한 관리자
     * @param request     : 수정 하는 공지사항 DTO
     * @return : 수정 성공 메시지
     */
    @Operation(summary = "공지사항 수정")
    @PutMapping("/notice")
    public ResponseDTO<?> updateNotice(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody NoticeDTO.UpdateRequest request) {
        noticeService.updateNotice(userDetails.getUserId(), request);
        return new ResponseDTO<>().ok(null, "공지사항 수정 되었습니다.");
    }

    /**
     * 공지사항 상태 수정 API
     * @param userDetails : 로그인한 관리자
     * @param request     : 수정하는 공지사항 상태 DTO
     * @return : 수정 성공 메시지
     */
    @Operation(summary = "공지사항 상태 수정")
    @PutMapping("/notice/status")
    public ResponseDTO<?> changeStatus(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody NoticeDTO.StatusRequest request) {
        noticeService.changeStatus(userDetails.getUserId(), request);
        return new ResponseDTO<>().ok(null, "수정 되었습니다.");
    }
}
