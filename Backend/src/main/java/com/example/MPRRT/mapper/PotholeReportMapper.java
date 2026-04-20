package com.example.MPRRT.mapper;

import com.example.MPRRT.dto.PotholeReportRequestDTO;
import com.example.MPRRT.dto.PotholeReportResponseDTO;
import com.example.MPRRT.entity.Location;
import com.example.MPRRT.entity.PotholeReport;
import com.example.MPRRT.entity.User;
import com.example.MPRRT.enums.ReportStatus;

public final class PotholeReportMapper {

    private PotholeReportMapper() {
    }

    public static PotholeReport toEntity(PotholeReportRequestDTO dto, User reportedBy, Location location) {
        if (dto == null) {
            return null;
        }

        PotholeReport entity = new PotholeReport();
        entity.setDescription(dto.getDescription());
        entity.setSeverityLevel(dto.getSeverity());
        entity.setImageUrl(dto.getImagePath());
        entity.setContactNumber(dto.getContactNumber());
        entity.setContactNumber(dto.getContactNumber());
        entity.setReportedBy(reportedBy);
        entity.setLocation(location);
        entity.setReportStatus(ReportStatus.REPORTED);
        return entity;
    }

    public static PotholeReportResponseDTO toResponseDTO(PotholeReport entity) {
        if (entity == null) {
            return null;
        }

        PotholeReportResponseDTO dto = new PotholeReportResponseDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setSeverity(entity.getSeverityLevel());
        dto.setStatus(entity.getReportStatus());
        dto.setLocationId(entity.getLocation() == null ? null : entity.getLocation().getId());
        dto.setReportedById(entity.getReportedBy() == null ? null : entity.getReportedBy().getId());
        dto.setReportedDate(entity.getCreatedAt());
        dto.setImagePath(entity.getImageUrl());
        return dto;
    }
}