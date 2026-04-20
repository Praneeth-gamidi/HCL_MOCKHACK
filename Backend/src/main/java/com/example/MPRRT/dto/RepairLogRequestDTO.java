package com.example.MPRRT.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairLogRequestDTO {
    private Long workOrderId;
    private Long technicianId;
    private String description;
    private BigDecimal cost;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String materialsUsed;
}
