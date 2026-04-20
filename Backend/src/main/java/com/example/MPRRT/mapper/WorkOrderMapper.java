package com.example.MPRRT.mapper;

import com.example.MPRRT.dto.WorkOrderRequestDTO;
import com.example.MPRRT.dto.WorkOrderResponseDTO;
import com.example.MPRRT.entity.PotholeReport;
import com.example.MPRRT.entity.User;
import com.example.MPRRT.entity.WorkOrder;
import com.example.MPRRT.enums.WorkStatus;

public final class WorkOrderMapper {

    private WorkOrderMapper() {
    }

    public static WorkOrder toEntity(WorkOrderRequestDTO dto, PotholeReport potholeReport, User assignedTo, User assignedBy) {
        if (dto == null) {
            return null;
        }

        WorkOrder entity = new WorkOrder();
        entity.setPotholeReport(potholeReport);
        entity.setAssignedTo(assignedTo);
        entity.setAssignedBy(assignedBy);
        entity.setExpectedCompletionDate(dto.getTargetCompletionDate());
        entity.setWorkStatus(WorkStatus.ASSIGNED);
        return entity;
    }

    public static WorkOrderResponseDTO toResponseDTO(WorkOrder entity) {
        if (entity == null) {
            return null;
        }

        WorkOrderResponseDTO dto = new WorkOrderResponseDTO();
        dto.setId(entity.getId());
        dto.setPotholeReportId(entity.getPotholeReport() == null ? null : entity.getPotholeReport().getId());
        dto.setAssignedToId(entity.getAssignedTo() == null ? null : entity.getAssignedTo().getId());
        dto.setStatus(entity.getWorkStatus());
        dto.setCreatedDate(entity.getCreatedAt());
        dto.setTargetCompletionDate(entity.getExpectedCompletionDate());
        dto.setCompletionDate(entity.getActualCompletionDate());
        return dto;
    }
}