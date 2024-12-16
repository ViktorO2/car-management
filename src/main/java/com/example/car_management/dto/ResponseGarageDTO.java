package com.example.car_management.dto;

import java.util.List;

public class ResponseGarageDTO {
    private Long id;
    private String name;
    private String city;
    private String location;
    private int capacity;
    private List<ResponseCarDTO> cars;

    public ResponseGarageDTO(Long id, String name, String city, String location, int capacity, List<ResponseCarDTO> cars) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.location = location;
        this.capacity = capacity;
        this.cars = cars;
    }

    public ResponseGarageDTO() {
    }

    public Long getId() {
        return id;
    }

    public ResponseGarageDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResponseGarageDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ResponseGarageDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ResponseGarageDTO setLocation(String location) {
        this.location = location;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public ResponseGarageDTO setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public List<ResponseCarDTO> getCars() {
        return cars;
    }

    public ResponseGarageDTO setCars(List<ResponseCarDTO> cars) {
        this.cars = cars;
        return this;
    }
}
