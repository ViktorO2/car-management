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
    List<Maintenance> findByCarId(Long carId);

    List<Maintenance> findByGarageId(Long garageId);

    List<Maintenance> findByScheduledDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT COUNT(m) FROM Maintenance m WHERE m.garage.id = :garageId AND m.scheduledDate = :date")
    int countByGarageAndDate(@Param("garageId") Long garageId, @Param("date") LocalDate date);

    @Query(value = "SELECT DATE_FORMAT(m.scheduled_date, '%Y-%m') AS yearMonth, COUNT(m.id) " +
            "FROM maintenance m " +
            "WHERE m.scheduled_date BETWEEN :startDate AND :endDate " +
            "GROUP BY yearMonth " +
            "ORDER BY yearMonth", nativeQuery = true)
    List<Object[]> countRequestsByMonth(@Param("garageId") Long garageId,
                                        @Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);
}
