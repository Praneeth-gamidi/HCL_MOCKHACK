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

import com.example.MPRRT.dto.WorkOrderRequestDTO;
import com.example.MPRRT.dto.WorkOrderResponseDTO;
import com.example.MPRRT.enums.WorkStatus;
import com.example.MPRRT.service.WorkOrderService;

@RestController
@RequestMapping("/api/work-orders")
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @PostMapping
    public ResponseEntity<WorkOrderResponseDTO> createWorkOrder(@RequestBody WorkOrderRequestDTO request,
            @RequestParam Long supervisorId) {
        WorkOrderResponseDTO created = workOrderService.createWorkOrder(request, supervisorId);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<WorkOrderResponseDTO>> getWorkOrders(@RequestParam(required = false) Long engineerId) {
        if (engineerId != null) {
            return ResponseEntity.ok(workOrderService.getWorkOrdersByEngineer(engineerId));
        }
        return ResponseEntity.ok(workOrderService.getAllWorkOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkOrderResponseDTO> getWorkOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(workOrderService.getWorkOrderById(id));
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<WorkOrderResponseDTO> assignEngineer(@PathVariable Long id,
            @RequestParam Long engineerId) {
        return ResponseEntity.ok(workOrderService.assignEngineer(id, engineerId));
    }

    @PutMapping("/{id}/update-status")
    public ResponseEntity<WorkOrderResponseDTO> updateStatus(@PathVariable Long id,
            @RequestParam WorkStatus status) {
        return ResponseEntity.ok(workOrderService.updateStatus(id, status));
    }
}
