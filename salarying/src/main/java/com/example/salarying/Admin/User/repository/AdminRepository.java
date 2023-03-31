package com.example.salarying.Admin.User.repository;

import com.example.salarying.Admin.User.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByAdminEmail(String email);

    Optional<Admin> findAdminById(Long Id);

    Admin findByRole(String role);

    List<Admin> findAllByRole(String role);
}
