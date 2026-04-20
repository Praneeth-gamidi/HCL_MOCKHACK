package com.example.MPRRT.dto;

import com.example.MPRRT.enums.ReportStatus;
import com.example.MPRRT.enums.SeverityLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PotholeReportRequestDTO {
    private String description;
    private SeverityLevel severity;
    private Long locationId;
    private Long reportedById;
    private String imagePath;
    private String contactNumber;
}
