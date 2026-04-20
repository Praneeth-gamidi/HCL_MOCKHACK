package com.example.MPRRT.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MPRRT.entity.Sla;
import com.example.MPRRT.enums.SeverityLevel;

public interface SlaRepository extends JpaRepository<Sla, Long> {
    Optional<Sla> findBySeverityLevel(SeverityLevel severityLevel);
}