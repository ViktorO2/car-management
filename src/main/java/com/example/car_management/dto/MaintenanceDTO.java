package com.example.car_management.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MaintenanceDTO {

    private Long id;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long carId;
    private Long garageId;
}
