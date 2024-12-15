package com.example.car_management.controllers;

import com.example.car_management.dto.GarageDTO;
import com.example.car_management.service.GarageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("garages")
@CrossOrigin(origins = "http://localhost:3000")
public class GarageController {
    private final GarageService garageService;


    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping
    public ResponseEntity<List<GarageDTO>> getAllGarages(@RequestParam(value = "city", required = false) String city) {
        List<GarageDTO> garages = garageService.getAllGarages(city);
        return ResponseEntity.ok(garages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarageDTO> getGarageById(@PathVariable Long id){
        return ResponseEntity.ok(garageService.getGarageById(id));
    }

    @PostMapping
    public ResponseEntity<GarageDTO> createGarage(@RequestBody GarageDTO garageDTO) {
        return ResponseEntity.ok(garageService.createGarage(garageDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GarageDTO> updateGarage(@PathVariable Long id ,@RequestBody GarageDTO garageDTO){
        return ResponseEntity.ok(garageService.updateGarage(id,garageDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGarage(@PathVariable Long id) {
        garageService.deleteGarage(id);
        return ResponseEntity.noContent().build();
    }

}

