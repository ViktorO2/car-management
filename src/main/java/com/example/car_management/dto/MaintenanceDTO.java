package com.example.car_management.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;


public class MaintenanceDTO {

    private Long id;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long carId;
    private Long garageId;

    public MaintenanceDTO(Long id, String description, LocalDateTime startDate, LocalDateTime endDate, Long carId, Long garageId) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.carId = carId;
        this.garageId = garageId;
    }

    public MaintenanceDTO() {
    }

    public Long getId() {

        return id;
    }

    public MaintenanceDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MaintenanceDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public MaintenanceDTO setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public MaintenanceDTO setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public Long getCarId() {
        return carId;
    }

    public MaintenanceDTO setCarId(Long carId) {
        this.carId = carId;
        return this;
    }

    public Long getGarageId() {
        return garageId;
    }

    public MaintenanceDTO setGarageId(Long garageId) {
        this.garageId = garageId;
        return this;
    }
}
