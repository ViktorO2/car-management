package com.example.car_management.repository;

import com.example.car_management.entity.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GarageRepository extends JpaRepository<Garage,Long> {
    List<Garage> findByCity(String city);
    @Query(value = "SELECT m.scheduled_date AS date, " +
            "COUNT(m.id) AS requests, " +
            "(g.capacity - IFNULL(COUNT(m.id), 0)) AS available_capacity " +
            "FROM garage g " +
            "LEFT JOIN maintenance m ON m.garage_id = g.id AND m.scheduled_date BETWEEN :startDate AND :endDate " +
            "WHERE g.id = :garageId " +
            "GROUP BY m.scheduled_date, g.capacity " +
            "ORDER BY m.scheduled_date ASC", nativeQuery = true)
    List<Object[]> getGarageDailyAvailability(
            @Param("garageId") Long garageId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}


