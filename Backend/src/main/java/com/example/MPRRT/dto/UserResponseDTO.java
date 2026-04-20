package com.example.MPRRT.dto;

import com.example.MPRRT.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Boolean isActive;
}
