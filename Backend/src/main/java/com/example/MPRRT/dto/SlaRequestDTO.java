package com.example.MPRRT.dto;

import com.example.MPRRT.enums.SeverityLevel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlaRequestDTO {
    @NotNull(message = "Severity level is required")
    private SeverityLevel severity;
    
    @NotNull(message = "Resolution time hours is required")
    @Positive(message = "Resolution time must be positive")
    private Integer resolutionTimeHours;
    
    private String description;
    private Boolean isActive = true;
}
