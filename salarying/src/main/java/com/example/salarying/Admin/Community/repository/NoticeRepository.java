package com.example.salarying.Admin.Community.repository;

import com.example.salarying.Admin.Community.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Notice findNoticeById(Long noticeId);
}
