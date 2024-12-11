package com.example.car_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(nullable = false)
    private String licensePlate;
    @Column(nullable = false)
    private int availableSpots;


    @ManyToMany
@JoinTable(
        name="car_garage",
        joinColumns = @JoinColumn(name = "car_id"),
        inverseJoinColumns = @JoinColumn(name="garage_id")
)
private List<Garage> garages=new ArrayList<>();

}
