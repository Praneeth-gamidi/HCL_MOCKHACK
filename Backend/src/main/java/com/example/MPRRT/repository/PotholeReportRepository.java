package com.example.MPRRT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MPRRT.entity.PotholeReport;

public interface PotholeReportRepository extends JpaRepository<PotholeReport, Long> {
}