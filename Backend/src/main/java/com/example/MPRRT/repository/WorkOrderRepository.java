package com.example.MPRRT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MPRRT.entity.WorkOrder;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
}