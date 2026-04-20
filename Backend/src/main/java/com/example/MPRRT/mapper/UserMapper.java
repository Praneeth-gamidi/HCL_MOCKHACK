package com.example.MPRRT.mapper;

import com.example.MPRRT.dto.UserRequestDTO;
import com.example.MPRRT.dto.UserResponseDTO;
import com.example.MPRRT.entity.User;

public final class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(UserRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        User entity = new User();
        entity.setName(buildFullName(dto.getFirstName(), dto.getLastName()));
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setZipCode(dto.getZipCode());
        entity.setIsActive(true);
        return entity;
    }

    public static UserResponseDTO toResponseDTO(User entity) {
        if (entity == null) {
            return null;
        }

        String[] names = splitName(entity.getName());

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(entity.getId());
        dto.setFirstName(names[0]);
        dto.setLastName(names[1]);
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhoneNumber());
        dto.setRole(entity.getRole());
        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setZipCode(entity.getZipCode());
        dto.setIsActive(entity.getIsActive());
        dto.setCreatedDate(entity.getCreatedAt());
        dto.setUpdatedDate(entity.getUpdatedAt());
        return dto;
    }

    private static String buildFullName(String firstName, String lastName) {
        String first = firstName == null ? "" : firstName.trim();
        String last = lastName == null ? "" : lastName.trim();

        if (first.isEmpty()) {
            return last;
        }
        if (last.isEmpty()) {
            return first;
        }
        return first + " " + last;
    }

    private static String[] splitName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return new String[] {null, null};
        }

        String[] parts = fullName.trim().split("\\s+", 2);
        if (parts.length == 1) {
            return new String[] {parts[0], null};
        }
        return new String[] {parts[0], parts[1]};
    }
}