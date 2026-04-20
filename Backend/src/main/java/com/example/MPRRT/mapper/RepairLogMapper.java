package com.example.MPRRT.mapper;

import com.example.MPRRT.dto.RepairLogRequestDTO;
import com.example.MPRRT.dto.RepairLogResponseDTO;
import com.example.MPRRT.entity.RepairLog;
import com.example.MPRRT.entity.User;
import com.example.MPRRT.entity.WorkOrder;

public final class RepairLogMapper {

    private RepairLogMapper() {
    }

    public static RepairLog toEntity(RepairLogRequestDTO dto, WorkOrder workOrder, User updatedBy) {
        if (dto == null) {
            return null;
        }

        RepairLog entity = new RepairLog();
        entity.setWorkOrder(workOrder);
        entity.setUpdatedBy(updatedBy);
        entity.setUpdateDescription(dto.getDescription());
        entity.setUpdatedAt(dto.getEndDate());
        return entity;
    }

    public static RepairLogResponseDTO toResponseDTO(RepairLog entity) {
        if (entity == null) {
            return null;
        }

        RepairLogResponseDTO dto = new RepairLogResponseDTO();
        dto.setId(entity.getId());
        dto.setWorkOrderId(entity.getWorkOrder() == null ? null : entity.getWorkOrder().getId());
        dto.setTechnicianId(entity.getUpdatedBy() == null ? null : entity.getUpdatedBy().getId());
        dto.setDescription(entity.getUpdateDescription());
        dto.setStatus(entity.getWorkOrder() == null ? null : entity.getWorkOrder().getWorkStatus());
        dto.setCreatedDate(entity.getUpdatedAt());
        return dto;
    }
}