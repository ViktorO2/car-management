package com.example.car_management.repository;

import com.example.car_management.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    @Query("SELECT MIN(m.scheduledDate) FROM Maintenance m")
    LocalDate findStartDate();
    @Query("SELECT MAX(m.scheduledDate) FROM Maintenance m")
    LocalDate findLastDate();
    @Query(value = "SELECT * FROM maintenance m " +
            "WHERE (:carId IS NULL OR m.car_id = :carId) " +
            "AND (:garageId IS NULL OR m.garage_id = :garageId) " +
            "AND m.scheduled_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Maintenance> findMaintenanceByFilters(
            @Param("carId") Long carId,
            @Param("garageId") Long garageId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT DATE(m.scheduled_date) AS date, COUNT(m.id) AS requests " +
            "FROM maintenance m " +
            "WHERE m.garage_id = :garageId " +
            "AND m.scheduled_date >= :startMonth " +
            "AND m.scheduled_date <= :endMonth " +
            "GROUP BY DATE(m.scheduled_date) " +
            "ORDER BY DATE(m.scheduled_date) ASC", nativeQuery = true)
    List<Object[]> getMonthlyReport(
            @Param("garageId") Long garageId,
            @Param("startMonth") LocalDate startDate,
            @Param("endMonth") LocalDate endDate);
}
