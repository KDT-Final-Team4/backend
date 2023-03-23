package com.example.salarying.Admin.Community.service.Impl;

import com.example.salarying.Admin.Community.dto.NoticeDTO;
import com.example.salarying.Admin.Community.entity.Notice;
import com.example.salarying.Admin.Community.repository.NoticeRepository;
import com.example.salarying.Admin.Community.service.NoticeService;
import com.example.salarying.Admin.User.entity.Admin;
import com.example.salarying.Admin.User.repository.AdminRepository;
import com.example.salarying.Corporation.User.exception.UserException;
import com.example.salarying.Corporation.User.exception.UserExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final AdminRepository adminRepository;

    /**
     * 관리자 공지사항 등록
     *
     * @param adminId 관지라 id
     * @param request 등록하고자 하는 공지사항 정보 DTO
     * @return 등록한 공지사항 정보
     */

    @Override
    public NoticeDTO.NoticeResponse insertNotice(Long adminId, NoticeDTO.NoticeRequest request) {
        Optional<Admin> admin = adminRepository.findById(adminId);
        if (admin.isPresent()) {
            Notice notice = request.toEntity(admin.get());
            noticeRepository.save(notice);
            return new NoticeDTO.NoticeResponse(notice);
        } else {
            throw new UserException(UserExceptionType.NOT_LOGGED_IN);
        }
    }
}
