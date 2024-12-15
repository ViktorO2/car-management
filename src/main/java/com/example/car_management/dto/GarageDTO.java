package com.example.car_management.dto;

import com.example.car_management.entity.Car;


import java.util.List;


public class GarageDTO {

    private Long id;
    private String name;
    private String location;
    private String city;
    private int capacity;


    public GarageDTO(Long id, String name, String location, String city, int capacity) {
        this.id=id;
        this.name = name;
        this.location = location;
        this.city = city;
        this.capacity = capacity;
    }

    public GarageDTO() {
    }

    public String getName() {
        return name;
    }

    public GarageDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public GarageDTO setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getCity() {
        return city;
    }

    public GarageDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public GarageDTO setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Long getId() {
        return id;
    }

    public GarageDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
