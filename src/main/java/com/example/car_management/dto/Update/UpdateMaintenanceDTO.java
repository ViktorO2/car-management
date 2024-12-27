package com.example.car_management.dto.Update;

import java.time.LocalDate;

public class UpdateMaintenanceDTO {
    private Long carId;
    private Long garageId;
    private String serviceType;
    private LocalDate scheduledDate;

    public UpdateMaintenanceDTO(Long carId, Long garageId, String serviceType, LocalDate scheduledDate) {
        this.carId = carId;
        this.garageId = garageId;
        this.serviceType = serviceType;
        this.scheduledDate = scheduledDate;
    }

    public UpdateMaintenanceDTO() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getGarageId() {
        return garageId;
    }

    public void setGarageId(Long garageId) {
        this.garageId = garageId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}
