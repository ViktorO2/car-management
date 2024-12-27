package com.example.car_management.dto.Update;

import java.util.List;

public class UpdateCarDTO {
        private String make;
        private String model;
        private int productionYear;
        private String licensePlate;
        private List<Long> garageIds;

    public UpdateCarDTO(String make, String model, int productionYear, String licensePlate, List<Long> garageIds) {
        this.make = make;
        this.model = model;
        this.productionYear = productionYear;
        this.licensePlate = licensePlate;
        this.garageIds = garageIds;
    }

    public UpdateCarDTO() {
    }

    public String getMake() {
        return make;
    }

    public UpdateCarDTO setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public UpdateCarDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public UpdateCarDTO setProductionYear(int productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public UpdateCarDTO setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public List<Long> getGarageIds() {
        return garageIds;
    }

    public UpdateCarDTO setGarageIds(List<Long> garageIds) {
        this.garageIds = garageIds;
        return this;
    }
}
