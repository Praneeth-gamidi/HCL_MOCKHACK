package com.example.MPRRT.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MPRRT.dto.RepairLogRequestDTO;
import com.example.MPRRT.dto.RepairLogResponseDTO;
import com.example.MPRRT.service.RepairLogService;

@RestController
@RequestMapping("/api/repair-logs")
public class RepairLogController {

    private final RepairLogService repairLogService;

    public RepairLogController(RepairLogService repairLogService) {
        this.repairLogService = repairLogService;
    }

    @PostMapping
    public ResponseEntity<RepairLogResponseDTO> createRepairLog(@RequestBody RepairLogRequestDTO request) {
        RepairLogResponseDTO created = repairLogService.createLog(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<RepairLogResponseDTO>> getRepairLogs(@RequestParam(required = false) Long workOrderId) {
        if (workOrderId != null) {
            return ResponseEntity.ok(repairLogService.getLogsByWorkOrder(workOrderId));
        }
        return ResponseEntity.ok(repairLogService.getAllLogs());
    }
}
