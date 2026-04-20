package com.example.MPRRT.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.MPRRT.dto.WorkOrderRequestDTO;
import com.example.MPRRT.dto.WorkOrderResponseDTO;
import com.example.MPRRT.entity.PotholeReport;
import com.example.MPRRT.entity.User;
import com.example.MPRRT.entity.WorkOrder;
import com.example.MPRRT.enums.ReportStatus;
import com.example.MPRRT.enums.Role;
import com.example.MPRRT.enums.WorkStatus;
import com.example.MPRRT.mapper.WorkOrderMapper;
import com.example.MPRRT.repository.WorkOrderRepository;

@Service
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;
    private final PotholeReportService reportService;
    private final UserService userService;
    private final SlaService slaService;

    public WorkOrderService(WorkOrderRepository workOrderRepository,
                            PotholeReportService reportService,
                            UserService userService,
                            SlaService slaService) {
        this.workOrderRepository = workOrderRepository;
        this.reportService = reportService;
        this.userService = userService;
        this.slaService = slaService;
    }

    public WorkOrderResponseDTO createWorkOrder(WorkOrderRequestDTO dto, Long supervisorId) {
        PotholeReport report = reportService.getReportEntityById(dto.getPotholeReportId());

        if (report.getReportStatus() != ReportStatus.APPROVED) {
            throw new RuntimeException("Work order cannot be created without an approved report");
        }

        User engineer = userService.getUserEntityById(dto.getAssignedToId());
        if (engineer.getRole() != Role.ENGINEER) {
            throw new RuntimeException("Assigned user must have ENGINEER role");
        }

        User supervisor = userService.getUserEntityById(supervisorId);

        WorkOrder workOrder = WorkOrderMapper.toEntity(dto, report, engineer, supervisor);
        workOrder.setStartDate(LocalDateTime.now());
        workOrder.setCreatedAt(LocalDateTime.now());

        reportService.updateReportStatus(report.getId(), ReportStatus.ASSIGNED);

        return WorkOrderMapper.toResponseDTO(workOrderRepository.save(workOrder));
    }

    public WorkOrderResponseDTO assignEngineer(Long workOrderId, Long engineerId) {
        WorkOrder workOrder = getWorkOrderEntityById(workOrderId);
        User engineer = userService.getUserEntityById(engineerId);
        if (engineer.getRole() != Role.ENGINEER) {
            throw new RuntimeException("Assigned user must have ENGINEER role");
        }
        workOrder.setAssignedTo(engineer);
        return WorkOrderMapper.toResponseDTO(workOrderRepository.save(workOrder));
    }

    public WorkOrderResponseDTO updateStatus(Long workOrderId, WorkStatus newStatus) {
        WorkOrder workOrder = getWorkOrderEntityById(workOrderId);

        if (workOrder.getWorkStatus() == WorkStatus.COMPLETED) {
            throw new RuntimeException("Completed repair cannot be modified");
        }

        workOrder.setWorkStatus(newStatus);

        if (newStatus == WorkStatus.COMPLETED) {
            workOrder.setActualCompletionDate(LocalDateTime.now());
            reportService.updateReportStatus(workOrder.getPotholeReport().getId(), ReportStatus.FIXED);
        } else if (newStatus == WorkStatus.IN_PROGRESS) {
            reportService.updateReportStatus(workOrder.getPotholeReport().getId(), ReportStatus.IN_PROGRESS);
            // Check SLA breach
            PotholeReport report = workOrder.getPotholeReport();
            if (slaService.isBreached(report.getSeverityLevel(), report.getCreatedAt())) {
                workOrder.setWorkStatus(WorkStatus.DELAYED);
            }
        }

        return WorkOrderMapper.toResponseDTO(workOrderRepository.save(workOrder));
    }

    public List<WorkOrderResponseDTO> getAllWorkOrders() {
        return workOrderRepository.findAll().stream()
                .map(WorkOrderMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public WorkOrderResponseDTO getWorkOrderById(Long id) {
        return WorkOrderMapper.toResponseDTO(getWorkOrderEntityById(id));
    }

    public List<WorkOrderResponseDTO> getWorkOrdersByEngineer(Long engineerId) {
        return workOrderRepository.findByAssignedToId(engineerId).stream()
                .map(WorkOrderMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public WorkOrder getWorkOrderEntityById(Long id) {
        return workOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkOrder not found with id: " + id));
    }
}
