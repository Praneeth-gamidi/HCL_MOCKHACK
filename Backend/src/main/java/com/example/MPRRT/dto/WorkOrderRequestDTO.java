package com.example.MPRRT.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderRequestDTO {
    @NotNull(message = "Pothole report ID is required")
    private Long potholeReportId;
    
    @NotNull(message = "Assigned to engineer ID is required")
    private Long assignedToId;
    
    @NotNull(message = "SLA ID is required")
    private Long slaId;
    
    private String description;
    
    @NotNull(message = "Target completion date is required")
    private LocalDateTime targetCompletionDate;
    
    private String priority;
}
