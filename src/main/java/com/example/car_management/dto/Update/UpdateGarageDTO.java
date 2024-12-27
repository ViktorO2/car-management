package com.example.car_management.dto.Update;

public class UpdateGarageDTO {
    private String name;
    private String city;
    private String location;
    private int capacity;

    public UpdateGarageDTO(String name, String city, String location, int capacity) {
        this.name = name;
        this.city = city;
        this.location = location;
        this.capacity = capacity;
    }

    public UpdateGarageDTO() {
    }

    public String getName() {
        return name;
    }

    public UpdateGarageDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UpdateGarageDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public UpdateGarageDTO setLocation(String location) {
        this.location = location;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public UpdateGarageDTO setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }
}
