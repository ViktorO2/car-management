package com.example.car_management.dto.Create;


public class CreateGarageDTO {
    private String name;
    private String location;
    private String city;
    private int capacity;


    public CreateGarageDTO(Long id, String name, String location, String city, int capacity) {
        this.name = name;
        this.location = location;
        this.city = city;
        this.capacity = capacity;

    }

    public CreateGarageDTO() {
    }

    public String getName() {
        return name;
    }

    public CreateGarageDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public CreateGarageDTO setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CreateGarageDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public CreateGarageDTO setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

}
