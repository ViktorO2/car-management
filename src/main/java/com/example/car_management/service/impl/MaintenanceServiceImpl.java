package com.example.car_management.service.impl;

import com.example.car_management.dto.Create.CreateMaintenanceDTO;
import com.example.car_management.dto.MonthlyRequestsReportDTO;
import com.example.car_management.dto.Response.ResponseMaintenanceDTO;
import com.example.car_management.dto.Update.UpdateMaintenanceDTO;
import com.example.car_management.entity.Car;
import com.example.car_management.entity.Garage;
import com.example.car_management.entity.Maintenance;
import com.example.car_management.repository.CarRepository;
import com.example.car_management.repository.GarageRepository;
import com.example.car_management.repository.MaintenanceRepository;
import com.example.car_management.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private GarageRepository garageRepository;

    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository, CarRepository carRepository, GarageRepository garageRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.carRepository = carRepository;
        this.garageRepository = garageRepository;
    }

    private Maintenance mapCreateMaintenanceDTOToMaintenance(CreateMaintenanceDTO createMaintenanceDTO) {
        Maintenance maintenance = new Maintenance();

        Car car = carRepository.findById(createMaintenanceDTO.getCarId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));

        Garage garage = garageRepository.findById(createMaintenanceDTO.getGarageId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found"));

        maintenance.setCar(car);
        maintenance.setGarage(garage);
        maintenance.setServiceType(createMaintenanceDTO.getServiceType());
        maintenance.setScheduledDate(createMaintenanceDTO.getScheduledDate());

        return maintenance;
    }

    private ResponseMaintenanceDTO mapMaintenanceToResponseMaintenanceDTO(Maintenance maintenance) {
        ResponseMaintenanceDTO responseMaintenanceDTO = new ResponseMaintenanceDTO(
                maintenance.getId(),
                maintenance.getCar().getId(),
                maintenance.getCar().getMake() + " " + maintenance.getCar().getModel(),
                maintenance.getGarage().getId(),
                maintenance.getGarage().getName(),
                maintenance.getServiceType(),
                maintenance.getScheduledDate()
        );
        return responseMaintenanceDTO;
    }


    @Override
    public ResponseMaintenanceDTO createMaintenance(CreateMaintenanceDTO createMaintenanceDTO) {
        Garage garage = garageRepository.findById(createMaintenanceDTO.getGarageId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found"));
        int scheduledCount = maintenanceRepository.countByGarageAndDate(createMaintenanceDTO.getGarageId(), createMaintenanceDTO.getScheduledDate());
        if (scheduledCount >= garage.getCapacity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No available slots on this date");
        }

        Maintenance maintenance = mapCreateMaintenanceDTOToMaintenance(createMaintenanceDTO);
        maintenance = maintenanceRepository.save(maintenance);
        return mapMaintenanceToResponseMaintenanceDTO(maintenance);
    }

    @Override
    public ResponseMaintenanceDTO updateMaintenance(Long id, UpdateMaintenanceDTO updateMaintenanceDTO) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Maintenance not found"));
        Garage garage = garageRepository.findById(updateMaintenanceDTO.getGarageId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found"));

        if (maintenanceRepository.countByGarageAndDate(updateMaintenanceDTO.getGarageId(), updateMaintenanceDTO.getScheduledDate()) >= garage.getCapacity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No available slots on this date");
        }

        maintenance.setServiceType(updateMaintenanceDTO.getServiceType());
        maintenance.setScheduledDate(updateMaintenanceDTO.getScheduledDate());
        maintenance.setGarage(garage);

        maintenance = maintenanceRepository.save(maintenance);
        return mapMaintenanceToResponseMaintenanceDTO(maintenance);
    }

    @Override
    public void deleteMaintenance(Long id) {

        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Maintenance not found"));
        maintenanceRepository.delete(maintenance);
    }

    @Override
    public ResponseMaintenanceDTO getMaintenanceById(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Maintenance not found"));
        return mapMaintenanceToResponseMaintenanceDTO(maintenance);
    }

    @Override
    public List<ResponseMaintenanceDTO> getAllMaintenances(Long carId, Long garageId, LocalDate startDate, LocalDate endDate) {
        List<Maintenance> maintenances = maintenanceRepository.findByScheduledDateBetween(startDate, endDate);
        return maintenances.stream()
                .filter(m -> carId == null || m.getCar().getId().equals(carId))
                .filter(m -> garageId == null || m.getGarage().getId().equals(garageId))
                .map(this::mapMaintenanceToResponseMaintenanceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MonthlyRequestsReportDTO> getMonthlyRequestsReport(Long garageId, YearMonth startMonth, YearMonth endMonth) {
        List<Object[]> results = maintenanceRepository.countRequestsByMonth(
                garageId,
                startMonth.atDay(1),
                endMonth.atEndOfMonth()
        );

        List<MonthlyRequestsReportDTO> report = new ArrayList<>();
        for (Object[] row : results) {
            YearMonth month = row[0] != null ? YearMonth.parse(row[0].toString()) : null;
            int count = row[1] != null ? ((Number) row[1]).intValue() : 0;

            if (month != null) {
                report.add(new MonthlyRequestsReportDTO(month, count));
            }
        }

        return report;
    }
}
