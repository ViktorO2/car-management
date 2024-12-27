package com.example.car_management.dto.Response;

import java.time.LocalDate;

public class ResponseMaintenanceDTO {
    private Long id;
    private Long carId;
    private String carName;
    private Long garageId;
    private String garageName;
    private String serviceType;
    private LocalDate scheduledDate;

    public ResponseMaintenanceDTO(Long id, Long carId, String carName, Long garageId, String garageName, String serviceType, LocalDate scheduledDate) {
        this.id = id;
        this.carId = carId;
        this.carName = carName;
        this.garageId = garageId;
        this.garageName = garageName;
        this.serviceType = serviceType;
        this.scheduledDate = scheduledDate;
    }

    public ResponseMaintenanceDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
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

    public Long getGarageId() {
        return garageId;
    }

    public void setGarageId(Long garageId) {
        this.garageId = garageId;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }
}
