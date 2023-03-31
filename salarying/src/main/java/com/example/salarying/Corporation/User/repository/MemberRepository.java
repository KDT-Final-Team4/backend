package com.example.salarying.Corporation.User.repository;

import com.example.salarying.Corporation.User.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findById(Long Id);

    Long countByAdmin_Id(Long Id);
}
