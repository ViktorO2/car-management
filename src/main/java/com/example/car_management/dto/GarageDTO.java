package com.example.car_management.dto;

import com.example.car_management.entity.Car;
import lombok.*;

import java.util.List;

@Data
public class GarageDTO {
    private Long id;
    private String name;
    private String city;
    private String location;
    private int capacity;
    private List<Car> cars;
}
