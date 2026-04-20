package com.example.MPRRT.entity;
import java.time.LocalDateTime;

import com.example.MPRRT.enums.ReportStatus;
import com.example.MPRRT.enums.SeverityLevel;

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
import jakarta.persistence.GenerationType;
@Entity
@Table(name = "pothole_reports")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class PotholeReport {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // FK → users
    @ManyToOne
    @JoinColumn(name = "reported_by", nullable = false)
    private User reportedBy;
    // FK → locations
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    private String description;
    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

