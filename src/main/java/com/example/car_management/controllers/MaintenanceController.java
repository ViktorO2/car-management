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
            @RequestParam(value = "carId",required = false) Long carId,
            @RequestParam(value = "garageId",required = false) Long garageId,
            @RequestParam(value = "startDate",required = false) LocalDate startDate,
            @RequestParam(value = "endDate",required = false) LocalDate endDate) {
        List<ResponseMaintenanceDTO> responseMaintenanceDTOS = maintenanceService.getAllMaintenances(carId, garageId, startDate, endDate);
        return ResponseEntity.ok(responseMaintenanceDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMaintenanceDTO> getMaintenanceById(@PathVariable Long id){
        return ResponseEntity.ok(maintenanceService.getMaintenanceById(id));
    }
    @GetMapping("/monthlyRequestsReport")
    public ResponseEntity<List<MonthlyRequestsReportDTO>> getMonthlyReport(@RequestParam(value = "garageId") Long garageId,
                                                                           @RequestParam(value = "startMonth", required = false) String startDate,
                                                                           @RequestParam(value = "endMonth", required = false) String endDate) {
       List<MonthlyRequestsReportDTO> term=maintenanceService.getMonthlyRequestsReport(garageId,startDate,endDate);
       return ResponseEntity.ok(term);
    }


}
