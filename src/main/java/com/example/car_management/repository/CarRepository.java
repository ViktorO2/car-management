package com.example.car_management.repository;

import com.example.car_management.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findCarByMake(String make);
    List<Car> findCarByProductionYearBetween(int fromYear, int toYear);
    List<Car> findCarByGaragesId(Long garageId);

}
