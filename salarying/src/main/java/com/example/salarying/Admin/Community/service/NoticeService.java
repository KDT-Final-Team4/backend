package com.example.salarying.Admin.Community.service;

import com.example.salarying.Admin.Community.dto.NoticeDTO;

import java.util.List;

public interface NoticeService {
    NoticeDTO.NoticeResponse insertNotice(Long adminId, NoticeDTO.NoticeRequest request);
    List<NoticeDTO.NoticeList> noticeList();
    NoticeDTO.NoticeResponse noticeDetail(Long id);
    void deleteNotice(Long adminId, Long noticeId);
    void updateNotice(Long adminId, NoticeDTO.Update update);
}
