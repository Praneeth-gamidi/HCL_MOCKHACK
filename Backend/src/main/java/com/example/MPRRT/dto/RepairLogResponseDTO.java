package com.example.MPRRT.dto;

import com.example.MPRRT.enums.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairLogResponseDTO {
    private Long id;
    private Long workOrderId;
    private Long technicianId;
    private String description;
    private BigDecimal cost;
    private WorkStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String materialsUsed;
    private LocalDateTime createdDate;
}
