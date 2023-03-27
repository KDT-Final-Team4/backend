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
    @PostMapping("/admin/notice")
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
}
