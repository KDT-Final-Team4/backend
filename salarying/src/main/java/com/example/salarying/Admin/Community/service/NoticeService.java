package com.example.salarying.Admin.Community.service;

import com.example.salarying.Admin.Community.dto.NoticeDTO;
import com.example.salarying.Admin.Community.entity.Notice;
import com.example.salarying.global.jwt.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface NoticeService {
    NoticeDTO.NoticeResponse insertNotice(Long adminId, NoticeDTO.NoticeRequest request);
    List<NoticeDTO.NoticeList> noticeList(@AuthenticationPrincipal CustomUserDetails customUserDetails);
    NoticeDTO.NoticeResponse noticeDetail(Long id);
    void deleteNotice(Long adminId, Long noticeId);
    void updateNotice(Long adminId, NoticeDTO.UpdateRequest request);
    void changeStatus(Long adminId, NoticeDTO.NoticeStatusRequest request);
    Notice findNoticeId(Long noticeId);
}
