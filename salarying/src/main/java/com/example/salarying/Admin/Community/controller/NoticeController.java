package com.example.salarying.Admin.Community.controller;

import com.example.salarying.Admin.Community.dto.NoticeDTO;
import com.example.salarying.Admin.Community.service.NoticeService;
import com.example.salarying.global.dto.ResponseDTO;
import com.example.salarying.global.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 관라지 id로 공지사항 등록 API
     * @param userDetails : 로그인한 관리자
     * @param request     : 등록할 공지사항 정보 DTO
     * @return : 등록된 공지사항 DTO
     */
    @Operation(summary = "공지사항 등록")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/notice")
    public ResponseDTO<?> insertNotice(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody NoticeDTO.NoticeRequest request) {
        NoticeDTO.NoticeResponse responseDTO = noticeService.insertNotice(userDetails.getUserId(), request);
        return new ResponseDTO<>().ok(responseDTO, "공지사항 등록 완료");
    }
}
