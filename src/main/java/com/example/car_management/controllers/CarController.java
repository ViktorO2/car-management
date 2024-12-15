package com.example.car_management.controllers;

import com.example.car_management.dto.CarDTO;
import com.example.car_management.entity.Car;
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


    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars(
            @RequestParam(value = "make", required = false) String make,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "startYear", required = false) Integer startYear,
            @RequestParam(value = "endYear", required = false) Integer endYear) {

        List<CarDTO> cars = carService.getAllCars(make, model, startYear, endYear);
        return ResponseEntity.ok(cars);  // Return the list of cars with HTTP 200 OK
    }
    @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO){
        return ResponseEntity.ok(carService.createCar(carDTO));
    }
}
