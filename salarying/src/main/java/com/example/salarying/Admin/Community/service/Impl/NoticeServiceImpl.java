package com.example.salarying.Admin.Community.service.Impl;

import com.example.salarying.Admin.Community.dto.NoticeDTO;
import com.example.salarying.Admin.Community.entity.Notice;
import com.example.salarying.Admin.Community.exception.CommunityException;
import com.example.salarying.Admin.Community.exception.CommunityExceptionType;
import com.example.salarying.Admin.Community.repository.NoticeRepository;
import com.example.salarying.Admin.Community.service.NoticeService;
import com.example.salarying.Admin.User.entity.Admin;
import com.example.salarying.Admin.User.repository.AdminRepository;
import com.example.salarying.Corporation.User.exception.UserException;
import com.example.salarying.Corporation.User.exception.UserExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final AdminRepository adminRepository;

    /**
     * 관리자 공지사항 등록
     * @param adminId 관리자 id
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

    /**
     * 공지사항 목록 조회
     * @return 공지사항 목록
     */
    @Override
    public List<NoticeDTO.NoticeList> noticeList() {
        List<Notice> noticeList = noticeRepository.findAll();
        return noticeList.stream()
                .map(NoticeDTO.NoticeList::new)
                .collect(Collectors.toList());
    }

    /**
     * 공지사항 상세 정보 조회
     * @param id : 공지사항 id
     * @return : 공지사항 상세 정보
     */
    @Override
    public NoticeDTO.NoticeResponse noticeDetail(Long id) {

        Notice notice = noticeRepository.findNoticeById(id);
        if (notice == null) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_NOTICE);
        }
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
        adminRepository.findById(adminId).orElseThrow(() -> new UserException(UserExceptionType.NOT_LOGGED_IN));
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new CommunityException(CommunityExceptionType.NOT_EXIST_NOTICE));
        noticeRepository.delete(notice);
    }

//    /**
//     * 공지사항 수정
//     * @param adminId : 관리자 id
//     * @param update  : 공지사항 정보수정
//     */
//    @Override
//    @Transactional
//    public void updateNotice(Long adminId, NoticeDTO.Update update) {
//        adminRepository.findById(adminId).orElseThrow(() -> new UserException(UserExceptionType.NOT_LOGGED_IN));
//        if (checkUpdateDTO(update)) {
//            Notice notice = noticeRepository.findNoticeById(update.getId());
//
//            update.setContent(update.toEntity().getContent());
//            update.setTitle(update.toEntity().getTitle());
//            update.setStatus(update.toEntity().getStatus());
//            noticeRepository.save();
//        }
//    }

    public Boolean checkUpdateDTO(NoticeDTO.Update update) {
        if (update.getTitle() == null || update.getTitle().equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_TITLE);
        } else if (update.getContent() == null || update.getContent().equals("")) {
            throw new CommunityException(CommunityExceptionType.NOT_EXIST_CONTENT);
        } else {
            return true;
        }
    }

}