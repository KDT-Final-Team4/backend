package com.example.salarying.Corporation.Recruiting.repository;


import com.example.salarying.Corporation.Recruiting.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress,Long> {

    Optional<Progress> findProgressByRecruiting_IdAndRecruiting_Member_Id(Long recruitingId,Long userId);
}
