package com.example.MPRRT.entity;
import java.time.LocalDateTime;

import com.example.MPRRT.enums.ReportStatus;
import com.example.MPRRT.enums.SeverityLevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pothole_reports")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class PotholeReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "reported_by", nullable = false)
    private User reportedBy;
    
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    
    @Column(nullable = false)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeverityLevel severityLevel;
    
    private String imageUrl;
    private String contactNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportStatus reportStatus;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

