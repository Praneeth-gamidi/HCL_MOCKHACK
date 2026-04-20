package com.example.MPRRT.entity;
import java.time.LocalDateTime;

import com.example.MPRRT.enums.WorkStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data; 
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GenerationType;
@Entity
@Table(name = "work_orders")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // One report → one work order
    @OneToOne
    @JoinColumn(name = "report_id", unique = true)
    private PotholeReport potholeReport;
    // Engineer assigned to the work order
    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;
    // Supervisor who assigned the work order
    @ManyToOne
    @JoinColumn(name = "assigned_by")
    private User assignedBy;
    private LocalDateTime startDate;
    private LocalDateTime expectedCompletionDate;
    private LocalDateTime actualCompletionDate;
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;
    private LocalDateTime createdAt;
}

