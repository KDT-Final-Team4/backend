package com.example.salarying.Admin.Community.service;

import com.example.salarying.Admin.Community.dto.NoticeDTO;

public interface NoticeService {
    NoticeDTO.NoticeResponse insertNotice(Long adminId, NoticeDTO.NoticeRequest request);
}
