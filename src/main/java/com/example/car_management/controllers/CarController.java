package com.example.car_management.controllers;

import com.example.car_management.dto.Create.CreateCarDTO;
import com.example.car_management.dto.Response.ResponseCarDTO;
import com.example.car_management.dto.Update.UpdateCarDTO;
import com.example.car_management.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars")
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {
    public final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<ResponseCarDTO> createCar(@RequestBody CreateCarDTO createCarDTO){
        return ResponseEntity.status(201).body(carService.createCar(createCarDTO));
    }

    @GetMapping
    public ResponseEntity<List<ResponseCarDTO>> getAllCars(
            @RequestParam(required = false) String carMake,
            @RequestParam(required = false) Long garageIds,
            @RequestParam(required = false) Integer fromYear,
            @RequestParam(required = false) Integer toYear){
        return ResponseEntity.ok(carService.getAllCars(carMake,garageIds,fromYear,toYear));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> getCarById(@PathVariable Long id){
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> updateCar(@PathVariable Long id, @RequestBody UpdateCarDTO updateCarDTO){
        return ResponseEntity.ok(carService.updateCar(id,updateCarDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}

