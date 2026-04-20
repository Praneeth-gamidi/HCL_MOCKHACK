package com.example.MPRRT.dto;

import com.example.MPRRT.enums.SeverityLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PotholeReportRequestDTO {
    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Severity level is required")
    private SeverityLevel severity;

    @NotNull(message = "Location ID is required")
    private Long locationId;

    @NotNull(message = "Reported by ID is required")
    private Long reportedById;

    private String imagePath;

    @NotBlank(message = "Contact number is required")
    private String contactNumber;
}
