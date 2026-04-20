package com.example.MPRRT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MPRRT.entity.Sla;

public interface SlaRepository extends JpaRepository<Sla, Long> {
}