package com.example.MPRRT.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.MPRRT.dto.PotholeReportRequestDTO;
import com.example.MPRRT.dto.PotholeReportResponseDTO;
import com.example.MPRRT.entity.Location;
import com.example.MPRRT.entity.PotholeReport;
import com.example.MPRRT.entity.User;
import com.example.MPRRT.enums.ReportStatus;
import com.example.MPRRT.enums.Role;
import com.example.MPRRT.mapper.PotholeReportMapper;
import com.example.MPRRT.repository.PotholeReportRepository;

@Service
public class PotholeReportService {

    private final PotholeReportRepository reportRepository;
    private final UserService userService;
    private final LocationService locationService;

    public PotholeReportService(PotholeReportRepository reportRepository,
            UserService userService,
            LocationService locationService) {
        this.reportRepository = reportRepository;
        this.userService = userService;
        this.locationService = locationService;
    }

    public PotholeReportResponseDTO createReport(PotholeReportRequestDTO dto) {
        User citizen = userService.getUserEntityById(dto.getReportedById());
        if (citizen.getRole() != Role.CITIZEN) {
            throw new RuntimeException("Only CITIZEN can create a pothole report");
        }
        if (dto.getSeverity() == null) {
            throw new RuntimeException("Report must have a valid severity level");
        }
        Location location = locationService.getLocationEntityById(dto.getLocationId());

        PotholeReport report = PotholeReportMapper.toEntity(dto, citizen, location);
        report.setCreatedAt(LocalDateTime.now());
        report.setUpdatedAt(LocalDateTime.now());
        return PotholeReportMapper.toResponseDTO(reportRepository.save(report));
    }

    public List<PotholeReportResponseDTO> getAllReports() {
        return reportRepository.findAll().stream()
                .map(PotholeReportMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PotholeReportResponseDTO getReportById(Long id) {
        return PotholeReportMapper.toResponseDTO(getReportEntityById(id));
    }

    public List<PotholeReportResponseDTO> getReportsByUser(Long userId) {
        return reportRepository.findByReportedById(userId).stream()
                .map(PotholeReportMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PotholeReportResponseDTO approveReport(Long id) {
        PotholeReport report = getReportEntityById(id);
        if (report.getReportStatus() != ReportStatus.REPORTED
                && report.getReportStatus() != ReportStatus.UNDER_REVIEW) {
            throw new RuntimeException("Report cannot be approved in current status: " + report.getReportStatus());
        }
        report.setReportStatus(ReportStatus.APPROVED);
        report.setUpdatedAt(LocalDateTime.now());
        return PotholeReportMapper.toResponseDTO(reportRepository.save(report));
    }

    public PotholeReportResponseDTO rejectReport(Long id) {
        PotholeReport report = getReportEntityById(id);
        if (report.getReportStatus() == ReportStatus.REJECTED) {
            throw new RuntimeException("Report is already rejected");
        }
        if (report.getReportStatus() == ReportStatus.ASSIGNED
                || report.getReportStatus() == ReportStatus.IN_PROGRESS
                || report.getReportStatus() == ReportStatus.FIXED
                || report.getReportStatus() == ReportStatus.CLOSED) {
            throw new RuntimeException("Rejected report cannot be processed further");
        }
        report.setReportStatus(ReportStatus.REJECTED);
        report.setUpdatedAt(LocalDateTime.now());
        return PotholeReportMapper.toResponseDTO(reportRepository.save(report));
    }

    public void updateReportStatus(Long id, ReportStatus status) {
        PotholeReport report = getReportEntityById(id);
        report.setReportStatus(status);
        report.setUpdatedAt(LocalDateTime.now());
        reportRepository.save(report);
    }

    public PotholeReport getReportEntityById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PotholeReport not found with id: " + id));
    }
}
