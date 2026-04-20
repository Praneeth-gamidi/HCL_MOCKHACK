package com.example.MPRRT.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairLogRequestDTO {
    @NotNull(message = "Work order ID is required")
    private Long workOrderId;
    
    @NotNull(message = "Technician ID is required")
    private Long technicianId;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String materialsUsed;
}
