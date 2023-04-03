package com.example.salarying.Admin.Community.service;

import com.example.salarying.Admin.Community.dto.NoticeDTO;

import java.util.List;

public interface NoticeService {
    NoticeDTO.NoticeResponse insertNotice(Long adminId, NoticeDTO.NoticeRequest request);
    List<NoticeDTO.NoticeList> noticeList(Long adminId);
    NoticeDTO.NoticeResponse noticeDetail(Long id);
    void deleteNotice(Long adminId, Long noticeId);
    void updateNotice(Long adminId, NoticeDTO.UpdateRequest request);
    void changeStatus(Long adminId, NoticeDTO.NoticeStatusRequest request);
}
