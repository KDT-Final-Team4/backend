package com.example.salarying.Corporation.Recruiting.repository;

import com.example.salarying.Corporation.Recruiting.entity.Applicant;
import com.example.salarying.Corporation.Recruiting.entity.Recruiting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant,Long> {
    List<Applicant> findApplicantByRecruitingId(Long recruiting_Id);

    Optional<Applicant> findApplicantByApplicantEmailAndRecruitingAndProgressAndStatus(String email, Recruiting recruiting,String progress,String status);
}
