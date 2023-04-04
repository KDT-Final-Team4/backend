package com.example.salarying.Admin.Community.service.Impl;

import com.example.salarying.Admin.Community.dto.NoticeDTO;
import com.example.salarying.Admin.Community.entity.Notice;
import com.example.salarying.Admin.Community.exception.CommunityException;
import com.example.salarying.Admin.Community.exception.CommunityExceptionType;
import com.example.salarying.Admin.Community.repository.NoticeRepository;
import com.example.salarying.Admin.Community.service.NoticeService;
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
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final AdminService adminService;

    /**
     * 관리자 공지사항 등록
     * @param adminId 관리자 id
     * @param request 등록하고자 하는 공지사항 정보 DTO
     * @return 등록한 공지사항 정보
     */

    @Override
    public NoticeDTO.NoticeResponse insertNotice(Long adminId, NoticeDTO.NoticeRequest request) {
        Admin admin = adminService.findAdminById(adminId);
        Notice notice = request.toEntity(admin);
            noticeRepository.save(notice);
        return new NoticeDTO.NoticeResponse(notice);
    }

    /**
     * 공지사항 목록 조회
     * @return 공지사항 목록
     */
    @Override
    public List<NoticeDTO.NoticeList> noticeList(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<Notice> noticeList = noticeRepository.findAll();
        if (customUserDetails.getRole().equals("ADMIN") || customUserDetails.getRole().equals("SUPERADMIN")) {
            return noticeList.stream()
                    .map(NoticeDTO.NoticeList::new)
                    .collect(Collectors.toList());
        } else {
            return noticeList.stream()
                    .map(NoticeDTO.NoticeList::new)
                    .filter(NoticeDTO.NoticeList::getStatus)
                    .collect(Collectors.toList());
        }
    }

    /**
     * 공지사항 상세 정보 조회
     * @param id : 공지사항 id
     * @return : 공지사항 상세 정보
     */
    @Override
    public NoticeDTO.NoticeResponse noticeDetail(Long id) {
        Notice notice = findNoticeId(id);
        return new NoticeDTO.NoticeResponse(notice);
    }

    /**
     * 공지사항 삭제
     * @param adminId  : 관리자 id
     * @param noticeId : 삭제할 notice id
     */
    @Override
    @Transactional
    public void deleteNotice(Long adminId, Long noticeId) {
        adminService.findAdminById(adminId);
        Notice notice = findNoticeId(noticeId);
        noticeRepository.delete(notice);
    }

    /**
     * 공지사항 수정
     * @param adminId : 관리자 id
     * @param request : 공지사항 정보수정
     */
    @Override
    @Transactional
    public void updateNotice(Long adminId, NoticeDTO.UpdateRequest request) {
        adminService.findAdminById(adminId);
        Notice notice = findNoticeId(request.getId());
        if (checkRequestDTO(request.getTitle(), request.getContent())) {
            notice.updateNotice(
                    request.getTitle(),
                    request.getContent()
            );
            noticeRepository.save(notice);
        }
    }

    /**
     * 공지사항 상태 변경
     * @param adminId : 관리자 id
     * @param request : 공지사항 상태 수정
     */
    @Override
    public void changeStatus(Long adminId, NoticeDTO.NoticeStatusRequest request) {
        adminService.findAdminById(adminId);
        Notice notice = findNoticeId(request.getId());
        notice.statusUpdate(request.getStatus());
        noticeRepository.save(notice);
    }

    /**
     * Notice Id로 약관 찾는 함수
     * @param noticeId : Notice id
     * @return : 해당 Id를 가진 약관
     */
    @Override
    public Notice findNoticeId(Long noticeId) {
        Optional<Notice> notice = noticeRepository.findById(noticeId);
        if (notice.isPresent()) return notice.get();
        else throw new CommunityException(CommunityExceptionType.NOT_EXIST);
    }

    /**
     * DTO 형식 체크 함수
     * @param title   : 공지사항 제목
     * @param content : 공지사항 내용
     * @return
     */
    public Boolean checkRequestDTO(String title, String content) {
        if (title == null || title.equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_TITLE);
        } else if (content == null || content.equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_CONTENT);
        } else {
            return true;
        }
    }

}