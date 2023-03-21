package com.example.salarying.Corporation.Recruiting.repository;

import com.example.salarying.Corporation.Recruiting.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant,Long> {
    List<Applicant> findApplicantByRecruitingId(Long recruiting_Id);
}
