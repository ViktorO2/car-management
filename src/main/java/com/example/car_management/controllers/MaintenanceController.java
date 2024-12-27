package com.example.car_management.controllers;

import com.example.car_management.dto.Create.CreateMaintenanceDTO;
import com.example.car_management.dto.MonthlyRequestsReportDTO;
import com.example.car_management.dto.Response.ResponseCarDTO;
import com.example.car_management.dto.Response.ResponseMaintenanceDTO;
import com.example.car_management.dto.Update.UpdateMaintenanceDTO;
import com.example.car_management.service.MaintenanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("maintenance")
@CrossOrigin(origins = "http://localhost:3000")
public class MaintenanceController {
    @Autowired
    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }


    @PostMapping
    public ResponseEntity<ResponseMaintenanceDTO> createMaintenance(@Valid @RequestBody CreateMaintenanceDTO createMaintenanceDTO) {
        return ResponseEntity.ok(maintenanceService.createMaintenance(createMaintenanceDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMaintenanceDTO> updateMaintenance(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMaintenanceDTO updateMaintenanceDTO) {
        return ResponseEntity.ok(maintenanceService.updateMaintenance(id, updateMaintenanceDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
      return ResponseEntity.noContent().build();

    }
    @GetMapping
    public ResponseEntity<List<ResponseMaintenanceDTO>> getAllMaintenances(
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) Long garageId,
            @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(maintenanceService.getAllMaintenances(carId, garageId, startDate, endDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMaintenanceDTO> getMaintenanceById(@PathVariable Long id){
        return ResponseEntity.ok(maintenanceService.getMaintenanceById(id));
    }
    @GetMapping("/monthlyRequestsReport")
    public ResponseEntity<List<MonthlyRequestsReportDTO>> getMonthlyReport(
            @RequestParam(required = false) Long garageId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth startMonth,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth endMonth) {
        if (startMonth.isAfter(endMonth)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start month cannot be after end month");
        }
        List<MonthlyRequestsReportDTO> report = maintenanceService.getMonthlyRequestsReport(garageId, startMonth, endMonth);
        return ResponseEntity.ok(report);
    }

}
