package com.example.MPRRT.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MPRRT.dto.SlaRequestDTO;
import com.example.MPRRT.dto.SlaResponseDTO;
import com.example.MPRRT.service.SlaService;

@RestController
@RequestMapping("/api/slas")
public class SlaController {

    private final SlaService slaService;

    public SlaController(SlaService slaService) {
        this.slaService = slaService;
    }

    @PostMapping
    public ResponseEntity<SlaResponseDTO> createSla(@RequestBody SlaRequestDTO request) {
        return ResponseEntity.ok(slaService.createSla(request));
    }

    @GetMapping
    public ResponseEntity<List<SlaResponseDTO>> getAllSlas() {
        return ResponseEntity.ok(slaService.getAllSlas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlaResponseDTO> getSlaById(@PathVariable Long id) {
        return ResponseEntity.ok(slaService.getSlaById(id));
    }
}
