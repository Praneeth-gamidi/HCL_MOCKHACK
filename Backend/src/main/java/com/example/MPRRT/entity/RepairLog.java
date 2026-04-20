package com.example.MPRRT.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data; 
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
@Entity
@Table(name = "repair_logs")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class RepairLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // FK → work_orders
    @ManyToOne
    @JoinColumn(name = "work_order_id", nullable = false)
    private WorkOrder workOrder;
    private String updateDescription;
    // FK → users (ENGINEER)
    @ManyToOne
    @JoinColumn(name = "updated_by", nullable = false)
    private User updatedBy;
    private LocalDateTime updatedAt;
}

