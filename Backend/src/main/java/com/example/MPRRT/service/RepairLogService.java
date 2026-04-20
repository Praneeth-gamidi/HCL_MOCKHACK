package com.example.MPRRT.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.MPRRT.dto.RepairLogRequestDTO;
import com.example.MPRRT.dto.RepairLogResponseDTO;
import com.example.MPRRT.entity.RepairLog;
import com.example.MPRRT.entity.User;
import com.example.MPRRT.entity.WorkOrder;
import com.example.MPRRT.enums.Role;
import com.example.MPRRT.enums.WorkStatus;
import com.example.MPRRT.mapper.RepairLogMapper;
import com.example.MPRRT.repository.RepairLogRepository;

@Service
public class RepairLogService {

    private final RepairLogRepository repairLogRepository;
    private final WorkOrderService workOrderService;
    private final UserService userService;

    public RepairLogService(RepairLogRepository repairLogRepository,
                            WorkOrderService workOrderService,
                            UserService userService) {
        this.repairLogRepository = repairLogRepository;
        this.workOrderService = workOrderService;
        this.userService = userService;
    }

    public RepairLogResponseDTO createLog(RepairLogRequestDTO dto) {
        WorkOrder workOrder = workOrderService.getWorkOrderEntityById(dto.getWorkOrderId());

        if (workOrder.getWorkStatus() == WorkStatus.COMPLETED) {
            throw new RuntimeException("Completed repair cannot be modified");
        }

        User engineer = userService.getUserEntityById(dto.getTechnicianId());
        if (engineer.getRole() != Role.ENGINEER) {
            throw new RuntimeException("Only ENGINEER can add repair logs");
        }

        RepairLog log = RepairLogMapper.toEntity(dto, workOrder, engineer);
        log.setUpdatedAt(LocalDateTime.now());
        return RepairLogMapper.toResponseDTO(repairLogRepository.save(log));
    }

    public List<RepairLogResponseDTO> getLogsByWorkOrder(Long workOrderId) {
        return repairLogRepository.findByWorkOrderId(workOrderId).stream()
                .map(RepairLogMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<RepairLogResponseDTO> getAllLogs() {
        return repairLogRepository.findAll().stream()
                .map(RepairLogMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
