package com.example.car_management.dto;

import com.example.car_management.entity.Garage;
import java.util.List;


public class CarDTO {
    private Long id;
    private String make;
    private String model;
    private int productionYear;
    private String licensePlate;
    private List<Garage> garages;

    public CarDTO() {
    }

    public CarDTO(Long id,String make, String model, int productionYear, String licensePlate, List<Garage> garages) {
        this.id=id;
        this.make = make;
        this.model = model;
        this.productionYear = productionYear;
        this.licensePlate = licensePlate;
        this.garages = garages;
    }

    public String getMake() {
        return make;
    }

    public CarDTO setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public CarDTO setProductionYear(int productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public CarDTO setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public List<Garage> getGarages() {
        return garages;
    }

    public CarDTO setGarages(List<Garage> garages) {
        this.garages = garages;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CarDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
