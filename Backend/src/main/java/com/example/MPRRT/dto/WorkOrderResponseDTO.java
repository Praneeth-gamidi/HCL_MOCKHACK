package com.example.MPRRT.dto;

import com.example.MPRRT.enums.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderResponseDTO {
    private Long id;
    private Long potholeReportId;
    private Long assignedToId;
    private Long slaId;
    private String description;
    private WorkStatus status;
    private String priority;
    private LocalDateTime createdDate;
    private LocalDateTime targetCompletionDate;
    private LocalDateTime completionDate;
    private String remarks;
}
