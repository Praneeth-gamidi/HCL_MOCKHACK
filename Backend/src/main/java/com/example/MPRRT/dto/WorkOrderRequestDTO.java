package com.example.MPRRT.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderRequestDTO {
    private Long potholeReportId;
    private Long assignedToId;
    private Long slaId;
    private String description;
    private LocalDateTime targetCompletionDate;
    private String priority;
}
