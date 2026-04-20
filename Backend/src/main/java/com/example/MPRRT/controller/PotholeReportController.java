package com.example.MPRRT.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MPRRT.dto.PotholeReportRequestDTO;
import com.example.MPRRT.dto.PotholeReportResponseDTO;
import com.example.MPRRT.service.PotholeReportService;

@RestController
@RequestMapping("/api/reports")
public class PotholeReportController {

    private final PotholeReportService reportService;

    public PotholeReportController(PotholeReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<PotholeReportResponseDTO> submitReport(@RequestBody PotholeReportRequestDTO request) {
        PotholeReportResponseDTO created = reportService.createReport(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<PotholeReportResponseDTO>> getReports(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return ResponseEntity.ok(reportService.getReportsByUser(userId));
        }
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PotholeReportResponseDTO> getReportById(@PathVariable Long id) {
        PotholeReportResponseDTO result = reportService.getReportById(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<PotholeReportResponseDTO> approveReport(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.approveReport(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<PotholeReportResponseDTO> rejectReport(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.rejectReport(id));
    }
}
