package com.example.car_management.repository;

import com.example.car_management.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    @Query(value = "SELECT DISTINCT c.* FROM car c " +
            "JOIN garage_cars gc ON c.id = gc.car_id " +
            "JOIN garage g ON gc.garage_id = g.id " +
            "WHERE (:carMake IS NULL OR LOWER(c.make) = LOWER(:carMake)) " +
            "AND (:garageId IS NULL OR g.id = :garageId) " +
            "AND (:fromYear IS NULL OR c.production_year >= :fromYear) " +
            "AND (:toYear IS NULL OR c.production_year <= :toYear)", nativeQuery = true)
    List<Car> findCarsByFilters(
            @Param("carMake") String carMake,
            @Param("garageId") Long garageId,
            @Param("fromYear") Integer fromYear,
            @Param("toYear") Integer toYear);
}
