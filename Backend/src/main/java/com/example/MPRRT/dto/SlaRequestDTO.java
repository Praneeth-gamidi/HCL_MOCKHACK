package com.example.MPRRT.dto;

import com.example.MPRRT.enums.SeverityLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlaRequestDTO {
    private String name;
    private SeverityLevel severity;
    private Integer responseTimeHours;
    private Integer resolutionTimeHours;
    private String description;
    private Boolean isActive;
}
