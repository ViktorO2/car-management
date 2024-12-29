package com.example.car_management.controllers;

import com.example.car_management.dto.Create.CreateGarageDTO;
import com.example.car_management.dto.GarageDailyAvailabilityReportDTO;
import com.example.car_management.dto.Response.ResponseGarageDTO;
import com.example.car_management.dto.Update.UpdateGarageDTO;
import com.example.car_management.service.GarageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("garages")
@CrossOrigin(origins = "http://localhost:3000")
public class GarageController {
    private final GarageService garageService;
    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }
    @PostMapping
    public ResponseEntity<ResponseGarageDTO> createGarage(@RequestBody CreateGarageDTO createGarageDTO) {
        return ResponseEntity.ok(garageService.createGarage(createGarageDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseGarageDTO> updateGarage(@PathVariable Long id , @RequestBody UpdateGarageDTO updateGarageDTO){
        return ResponseEntity.ok(garageService.updateGarage(id,updateGarageDTO));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGarage(@PathVariable Long id) {
        garageService.deleteGarage(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<ResponseGarageDTO>> getAllGarages(@RequestParam(value = "city", required = false) String city) {
        return ResponseEntity.ok(garageService.getAllGarages(city));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseGarageDTO> getGarageById(@PathVariable Long id){
        return ResponseEntity.ok(garageService.getGarageById(id));
    }
    @GetMapping("/dailyAvailabilityReport")
    public ResponseEntity<List<GarageDailyAvailabilityReportDTO>> getDailyAvailabilityReport(
            @RequestParam(value = "garageId") Long garageId,
            @RequestParam(value = "startDate") LocalDate startDate,
            @RequestParam(value = "endDate") LocalDate endDate) {

        List<GarageDailyAvailabilityReportDTO> report=garageService.getGarageDailyReport(garageId,startDate,endDate);
        return ResponseEntity.ok(report);
    }
}

