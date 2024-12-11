package com.example.car_management.dto;

import com.example.car_management.entity.Garage;
import lombok.Data;

import java.util.List;

@Data
public class CarDTO {
    private Long id;
    private String make;
    private String model;
    private int productionYear;
    private String licensePlate;
    private List<Garage> garages;
}
