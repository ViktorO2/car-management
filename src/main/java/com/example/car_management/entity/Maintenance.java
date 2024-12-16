package com.example.car_management.entity;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id",nullable = false)
    private Car car;
    @ManyToOne
    @JoinColumn(name = "garage_id",nullable = false)
    private Garage garage;
    @Column(nullable = false)
    private String serviceType;
    @Column(nullable = false)
    private LocalDate scheduledDate;

    public Maintenance(Long id, Car car, Garage garage, String serviceType, LocalDate scheduledDate) {
        this.id = id;
        this.car = car;
        this.garage = garage;
        this.serviceType = serviceType;
        this.scheduledDate = scheduledDate;
    }

    public Maintenance() {
    }

    public Long getId() {
        return id;
    }

    public Maintenance setId(Long id) {
        this.id = id;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public Maintenance setCar(Car car) {
        this.car = car;
        return this;
    }

    public Garage getGarage() {
        return garage;
    }

    public Maintenance setGarage(Garage garage) {
        this.garage = garage;
        return this;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Maintenance setServiceType(String serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public Maintenance setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
        return this;
    }
}
