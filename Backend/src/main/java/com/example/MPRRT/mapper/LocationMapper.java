package com.example.MPRRT.mapper;

import com.example.MPRRT.dto.LocationRequestDTO;
import com.example.MPRRT.dto.LocationResponseDTO;
import com.example.MPRRT.entity.Location;

public final class LocationMapper {

    private LocationMapper() {
    }

    public static Location toEntity(LocationRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Location entity = new Location();
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setAreaName(dto.getAreaName());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        return entity;
    }

    public static LocationResponseDTO toResponseDTO(Location entity) {
        if (entity == null) {
            return null;
        }

        LocationResponseDTO dto = new LocationResponseDTO();
        dto.setId(entity.getId());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setAddress(entity.getAreaName());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        return dto;
    }
}