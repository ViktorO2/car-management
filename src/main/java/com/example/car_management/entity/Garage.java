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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "garage_cars",
            joinColumns = @JoinColumn(name = "garage_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private List<Car> cars = new ArrayList<>();


    public Garage(Long id, String name, String city, String location, int capacity, List<Car> cars) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.location = location;
        this.capacity = capacity;
        this.cars = cars;
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

    public List<Car> getCars() {
        return cars;
    }

    public Garage setCars(List<Car> cars) {
        this.cars = cars;
        return this;
    }

    public void addCar(Car car) {
        if (!cars.contains(car)) {
            cars.add(car);
            car.getGarages().add(this); // Взаимно обновяване
        }
    }

    public void removeCar(Car car) {
        cars.remove(car);
        car.getGarages().remove(this); // Взаимно премахване
    }
}
