package com.example.car_management.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private int capacity;


    public Garage(Long id, String name, String city, String location, int capacity) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.location = location;
        this.capacity = capacity;
    }

    public Garage() {

    }

    public Long getId() {
        return id;
    }

    public Garage setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Garage setName(String name) {
        this.name = name;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Garage setCity(String city) {
        this.city = city;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Garage setLocation(String location) {
        this.location = location;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public Garage setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }
}
