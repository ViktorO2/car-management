package com.example.car_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(nullable = false)
    private String status;


}
