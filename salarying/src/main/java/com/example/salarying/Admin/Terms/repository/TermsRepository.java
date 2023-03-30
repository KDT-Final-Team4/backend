package com.example.salarying.Admin.Terms.repository;

import com.example.salarying.Admin.Terms.entity.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TermsRepository extends JpaRepository<Terms, Long> {

    Boolean existsTermsByTypeAndVersion(String type, String version);

    List<Terms> findByType(String type);

    Optional<Terms> findById(Long Id);

    Boolean existsByTypeAndStatus(String type, String status);

    Terms findByTypeAndStatus(String type, String status);

}
