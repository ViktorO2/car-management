package com.example.car_management.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseCarDTO {
    private Long id;
    private String make;
    private String model;
    private int productionYear;
    private String licensePlate;
    private List<ResponseGarageDTO> garages;

    public ResponseCarDTO(Long id, String make, String model, int productionYear, String licensePlate, List<ResponseGarageDTO> garages) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.productionYear = productionYear;
        this.licensePlate = licensePlate;
        this.garages = garages;
    }

    public ResponseCarDTO() {
    }

    public Long getId() {
        return id;
    }

    public ResponseCarDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMake() {
        return make;
    }

    public ResponseCarDTO setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ResponseCarDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public ResponseCarDTO setProductionYear(int productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public ResponseCarDTO setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public List<ResponseGarageDTO> getGarages() {
        return garages;
    }

    public ResponseCarDTO setGarages(List<ResponseGarageDTO> garages) {
        this.garages = garages;
        return this;
    }
}
