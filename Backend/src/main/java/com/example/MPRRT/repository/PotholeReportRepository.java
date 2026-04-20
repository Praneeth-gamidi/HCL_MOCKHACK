package com.example.MPRRT.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MPRRT.entity.PotholeReport;
import com.example.MPRRT.enums.ReportStatus;

public interface PotholeReportRepository extends JpaRepository<PotholeReport, Long> {
    List<PotholeReport> findByReportedById(Long userId);
    List<PotholeReport> findByReportStatus(ReportStatus status);
}