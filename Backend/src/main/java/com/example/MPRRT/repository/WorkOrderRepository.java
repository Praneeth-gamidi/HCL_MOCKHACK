package com.example.MPRRT.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MPRRT.entity.WorkOrder;
import com.example.MPRRT.enums.WorkStatus;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
    Optional<WorkOrder> findByPotholeReportId(Long reportId);
    List<WorkOrder> findByAssignedToId(Long engineerId);
    List<WorkOrder> findByWorkStatus(WorkStatus status);
}