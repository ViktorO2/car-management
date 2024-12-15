package com.example.car_management.repository;

import com.example.car_management.entity.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageRepository extends JpaRepository<Garage,Long> {
    List<Garage> findByCity(String city);
}

