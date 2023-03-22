package com.example.salarying.Corporation.Recruiting.repository;


import com.example.salarying.Corporation.Recruiting.entity.Recruiting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitingRepository extends JpaRepository<Recruiting,Long> {

    List<Recruiting> findRecruitingByMemberId(Long userId);


}