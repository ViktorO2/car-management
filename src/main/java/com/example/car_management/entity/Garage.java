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

    @ManyToMany(mappedBy = "garages")
    private List<Car> cars=new ArrayList<>();
    @OneToMany(mappedBy = "garage")
    private List<Maintenance> maintenances;


}
