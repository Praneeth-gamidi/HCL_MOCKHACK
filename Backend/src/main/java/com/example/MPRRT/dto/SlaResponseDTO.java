package com.example.MPRRT.dto;

import com.example.MPRRT.enums.SeverityLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlaResponseDTO {
    private Long id;
    private String name;
    private SeverityLevel severity;
    private Integer responseTimeHours;
    private Integer resolutionTimeHours;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
