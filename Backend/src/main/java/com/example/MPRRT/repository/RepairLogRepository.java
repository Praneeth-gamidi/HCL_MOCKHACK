package com.example.MPRRT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MPRRT.entity.RepairLog;

public interface RepairLogRepository extends JpaRepository<RepairLog, Long> {
    List<RepairLog> findByWorkOrderId(Long workOrderId);
}