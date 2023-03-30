package com.example.salarying.Corporation.Recruiting.repository;


import com.example.salarying.Corporation.Recruiting.entity.Recruiting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecruitingRepository extends JpaRepository<Recruiting,Long> {

    List<Recruiting> findRecruitingByMemberId(Long userId);

    Optional<Recruiting> findRecruitingByIdAndMember_IdAndStatus(Long recruitingId, Long userId,String status);


}
