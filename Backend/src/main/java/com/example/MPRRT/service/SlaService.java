package com.example.MPRRT.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.MPRRT.dto.SlaRequestDTO;
import com.example.MPRRT.dto.SlaResponseDTO;
import com.example.MPRRT.entity.Sla;
import com.example.MPRRT.enums.SeverityLevel;
import com.example.MPRRT.mapper.SlaMapper;
import com.example.MPRRT.repository.SlaRepository;

@Service
public class SlaService {

    private final SlaRepository slaRepository;

    public SlaService(SlaRepository slaRepository) {
        this.slaRepository = slaRepository;
    }

    public SlaResponseDTO createSla(SlaRequestDTO dto) {
        Sla sla = SlaMapper.toEntity(dto);
        sla.setCreatedAt(LocalDateTime.now());
        return SlaMapper.toResponseDTO(slaRepository.save(sla));
    }

    public List<SlaResponseDTO> getAllSlas() {
        return slaRepository.findAll().stream()
                .map(SlaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public SlaResponseDTO getSlaById(Long id) {
        return SlaMapper.toResponseDTO(getSlaEntityById(id));
    }

    /**
     * Returns the SLA deadline (createdAt + resolutionTimeHours) for a given severity.
     */
    public LocalDateTime getDeadline(SeverityLevel severity, LocalDateTime createdAt) {
        Sla sla = slaRepository.findBySeverityLevel(severity)
                .orElseThrow(() -> new RuntimeException("No SLA configured for severity: " + severity));
        return createdAt.plusHours(sla.getResolutionTimeHours());
    }

    /**
     * Checks whether the work order has breached its SLA deadline.
     */
    public boolean isBreached(SeverityLevel severity, LocalDateTime createdAt) {
        return LocalDateTime.now().isAfter(getDeadline(severity, createdAt));
    }

    public Sla getSlaEntityById(Long id) {
        return slaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SLA not found with id: " + id));
    }
}
