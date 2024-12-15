package com.example.car_management.service;

import com.example.car_management.dto.CarDTO;
import com.example.car_management.dto.GarageDTO;

import java.util.List;

public interface CarService {

    List<CarDTO> getAllCars(String make, String model, Integer startYear, Integer endYear);

    CarDTO createCar(CarDTO carDTO);
}
