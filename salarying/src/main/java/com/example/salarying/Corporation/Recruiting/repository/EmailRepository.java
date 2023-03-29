package com.example.salarying.Corporation.Recruiting.repository;

import com.example.salarying.Corporation.Recruiting.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email,Long> {

    List<Email> findEmailByRecruiting_Member_Id(Long userId);
}
