package com.example.MPRRT.entity;
import java.time.LocalDateTime;

import com.example.MPRRT.enums.SeverityLevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data; 
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
@Entity
@Table(name = "sla")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Sla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private SeverityLevel severityLevel;
    private Integer resolutionTimeHours;
    private LocalDateTime createdAt;
}

