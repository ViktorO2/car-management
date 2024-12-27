package com.example.car_management.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String make;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private int productionYear;
    @Column(nullable = false, unique = true)
    private String licensePlate;
    @ManyToMany(mappedBy = "cars", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Garage> garages = new ArrayList<>();

    public Car(Long id, String make, String model, int productionYear, String licensePlate, List<Garage> garages) {

        this.id = id;
        this.make = make;
        this.model = model;
        this.productionYear = productionYear;
        this.licensePlate = licensePlate;
        this.garages = garages;
    }

    public Car() {

    }

    public Long getId() {
        return id;
    }

    public Car setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMake() {
        return make;
    }

    public Car setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public Car setProductionYear(int productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Car setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public List<Garage> getGarages() {
        return garages;
    }

    public Car setGarages(List<Garage> garages) {
        this.garages = garages;
        return this;
    }

    public void addGarage(Garage garage) {
        if (!garages.contains(garage)) {
            garages.add(garage);
            garage.getCars().add(this); // Взаимно обновяване
        }
    }

    public void removeGarage(Garage garage) {
        garages.remove(garage);
        garage.getCars().remove(this); // Взаимно премахване
    }
}


