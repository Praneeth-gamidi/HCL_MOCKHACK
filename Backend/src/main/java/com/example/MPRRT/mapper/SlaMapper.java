package com.example.MPRRT.mapper;

import com.example.MPRRT.dto.SlaRequestDTO;
import com.example.MPRRT.dto.SlaResponseDTO;
import com.example.MPRRT.entity.Sla;

public final class SlaMapper {

    private SlaMapper() {
    }

    public static Sla toEntity(SlaRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Sla entity = new Sla();
        entity.setSeverityLevel(dto.getSeverity());
        entity.setResolutionTimeHours(dto.getResolutionTimeHours());
        return entity;
    }

    public static SlaResponseDTO toResponseDTO(Sla entity) {
        if (entity == null) {
            return null;
        }

        SlaResponseDTO dto = new SlaResponseDTO();
        dto.setId(entity.getId());
        dto.setSeverity(entity.getSeverityLevel());
        dto.setResolutionTimeHours(entity.getResolutionTimeHours());
        dto.setCreatedDate(entity.getCreatedAt());
        return dto;
    }
}