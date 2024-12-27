package com.example.car_management.dto.Create;

import java.util.List;


public class CreateCarDTO {
    private String make;
    private String model;
    private int productionYear;
    private String licensePlate;
    private List<Long> garageIds;

    public CreateCarDTO() {
    }

    public CreateCarDTO(Long id, String make, String model, int productionYear, String licensePlate, List<Long> garageIds) {
        this.make = make;
        this.model = model;
        this.productionYear = productionYear;
        this.licensePlate = licensePlate;
        this.garageIds =garageIds;
    }

    public String getMake() {
        return make;
    }

    public CreateCarDTO setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CreateCarDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public CreateCarDTO setProductionYear(int productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public CreateCarDTO setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public List<Long> getGarageIds() {
        return garageIds;
    }

    public CreateCarDTO setGarageIds(List<Long> garageIds) {
        this.garageIds = garageIds;
        return this;
    }
}
