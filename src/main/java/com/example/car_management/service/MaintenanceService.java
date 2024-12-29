package com.example.car_management.service;

import com.example.car_management.dto.Create.CreateMaintenanceDTO;
import com.example.car_management.dto.MonthlyRequestsReportDTO;
import com.example.car_management.dto.Response.ResponseMaintenanceDTO;
import com.example.car_management.dto.Update.UpdateMaintenanceDTO;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface MaintenanceService {
    ResponseMaintenanceDTO createMaintenance(CreateMaintenanceDTO createMaintenanceDTO);
    ResponseMaintenanceDTO updateMaintenance(Long id, UpdateMaintenanceDTO updateMaintenanceDTO);
    void deleteMaintenance(Long id);
    ResponseMaintenanceDTO getMaintenanceById(Long id);
    List<ResponseMaintenanceDTO> getAllMaintenances(Long carId, Long garageId, LocalDate startDate, LocalDate endDate);
    List<MonthlyRequestsReportDTO> getMonthlyRequestsReport(Long garageId, String startDate, String  endDate);
}
