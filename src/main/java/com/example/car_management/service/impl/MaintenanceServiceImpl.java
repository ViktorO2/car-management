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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
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


    @Override
    @Transactional
    public ResponseMaintenanceDTO createMaintenance(CreateMaintenanceDTO createMaintenanceDTO) {
        Car car = carRepository.findById(createMaintenanceDTO.getCarId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        Garage garage = garageRepository.findById(createMaintenanceDTO.getGarageId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found"));
        Maintenance maintenance = mapCreateMaintenanceDTOToMaintenance(createMaintenanceDTO, car, garage);
        Maintenance newMaintenance = maintenanceRepository.save(maintenance);
        return mapMaintenanceToResponseMaintenanceDTO(newMaintenance);

    }


//    @Override
//    public ResponseMaintenanceDTO createMaintenance(CreateMaintenanceDTO createMaintenanceDTO) {
//        Garage garage = garageRepository.findById(createMaintenanceDTO.getGarageId())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found"));
//        int scheduledCount = maintenanceRepository.countByGarageAndDate(createMaintenanceDTO.getGarageId(), createMaintenanceDTO.getScheduledDate());
//        if (scheduledCount >= garage.getCapacity()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No available slots on this date");
//        }
//
//        Maintenance maintenance = mapCreateMaintenanceDTOToMaintenance(createMaintenanceDTO);
//        maintenance = maintenanceRepository.save(maintenance);
//        return mapMaintenanceToResponseMaintenanceDTO(maintenance);
//    }


    @Override
    @Transactional
    public ResponseMaintenanceDTO updateMaintenance(Long id, UpdateMaintenanceDTO updateMaintenanceDTO) {
        Maintenance existingMaintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Maintenance record not found!"));

        Car car = updateMaintenanceDTO.getCarId() != null ? carRepository.findById(updateMaintenanceDTO.getCarId()).orElse(null) : null;
        Garage garage = updateMaintenanceDTO.getGarageId() != null ? garageRepository.findById(updateMaintenanceDTO.getGarageId()).orElse(null) : null;

        Maintenance updatedMaintenance = mapUpdateDTOToMaintenance(existingMaintenance, updateMaintenanceDTO, car, garage);
        Maintenance savedMaintenance = maintenanceRepository.save(updatedMaintenance);

        return mapMaintenanceToResponseMaintenanceDTO(savedMaintenance);
    }


//    @Override
//    public ResponseMaintenanceDTO updateMaintenance(Long id, UpdateMaintenanceDTO updateMaintenanceDTO) {
//        Maintenance maintenance = maintenanceRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Maintenance not found"));
//        Garage garage = garageRepository.findById(updateMaintenanceDTO.getGarageId())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found"));
//
//        if (maintenanceRepository.countByGarageAndDate(updateMaintenanceDTO.getGarageId(), updateMaintenanceDTO.getScheduledDate()) >= garage.getCapacity()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No available slots on this date");
//        }
//
//        maintenance.setServiceType(updateMaintenanceDTO.getServiceType());
//        maintenance.setScheduledDate(updateMaintenanceDTO.getScheduledDate());
//        maintenance.setGarage(garage);
//
//        maintenance = maintenanceRepository.save(maintenance);
//        return mapMaintenanceToResponseMaintenanceDTO(maintenance);
//    }

    @Override
    @Transactional
    public void deleteMaintenance(Long id) {

        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Maintenance not found"));
        maintenanceRepository.delete(maintenance);
    }

    @Override
    public ResponseMaintenanceDTO getMaintenanceById(Long id) {
        Optional<Maintenance> maintenance = maintenanceRepository.findById(id);
        if (maintenance.isPresent()) {
            return mapMaintenanceToResponseMaintenanceDTO(maintenance.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Maintenance not found");
    }

    @Override
    public List<ResponseMaintenanceDTO> getAllMaintenances(Long carId, Long garageId, LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            startDate = maintenanceRepository.findStartDate();
        }
        if (endDate == null) {
            endDate = maintenanceRepository.findLastDate();
        }
        List<Maintenance> maintenances = maintenanceRepository.findMaintenanceByFilters(carId, garageId, startDate, endDate);
        return maintenances.stream()
                .map(this::mapMaintenanceToResponseMaintenanceDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<MonthlyRequestsReportDTO> getMonthlyRequestsReport(Long garageId, String startDate, String endDate) {
        YearMonth start = YearMonth.parse(startDate);
        YearMonth end = YearMonth.parse(endDate);

        LocalDate startOfMonth = start.atDay(1);
        LocalDate endOfMonth = end.atEndOfMonth();

        List<Object[]> queryResult = maintenanceRepository.getMonthlyReport(garageId, startOfMonth, endOfMonth);

        Map<YearMonth, Integer> monthRequestCounts = new HashMap<>();
        for (Object[] row : queryResult) {
            Date date = (Date) row[0];
            LocalDate localDate = LocalDate.parse(date.toString());
            YearMonth yearMonth = YearMonth.of(localDate.getYear(), localDate.getMonthValue());
            Integer count = ((Number) row[1]).intValue();

            monthRequestCounts.merge(yearMonth, count, Integer::sum);
        }
        List<YearMonth> monthsInRange = generateYearMonthRange(start, end);
        return monthsInRange.stream()
                .map(month -> new MonthlyRequestsReportDTO(month, monthRequestCounts.getOrDefault(month, 0)))
                .collect(Collectors.toList());
    }

    private List<YearMonth> generateYearMonthRange(YearMonth start, YearMonth end) {
        List<YearMonth> months = new ArrayList<>();
        YearMonth current = start;
        while (!current.isAfter(end)) {
            months.add(current);
            current = current.plusMonths(1);
        }
        return months;
    }

    private Maintenance mapUpdateDTOToMaintenance(Maintenance existMaintenance, UpdateMaintenanceDTO updateMaintenanceDTO, Car car, Garage garage) {
        if (existMaintenance == null || updateMaintenanceDTO == null) {
            throw new IllegalArgumentException("Cannot be null");
        }

        existMaintenance.setServiceType(updateMaintenanceDTO.getServiceType());
        existMaintenance.setScheduledDate(updateMaintenanceDTO.getScheduledDate());
        if (car != null) {
            existMaintenance.setCar(car);
        }
        if (garage != null) {
            existMaintenance.setGarage(garage);
        }
        return existMaintenance;
    }

    private Maintenance mapCreateMaintenanceDTOToMaintenance(CreateMaintenanceDTO createMaintenanceDTO, Car car, Garage garage) {
        if (createMaintenanceDTO == null || car == null || garage == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car, Garage, and CreateMaintenanceDTO cannot be null");
        }

        Maintenance maintenance = new Maintenance();
        maintenance.setServiceType(createMaintenanceDTO.getServiceType());
        maintenance.setScheduledDate(createMaintenanceDTO.getScheduledDate());
        maintenance.setCar(car);
        maintenance.setGarage(garage);
        return maintenance;
    }

    private ResponseMaintenanceDTO mapMaintenanceToResponseMaintenanceDTO(Maintenance maintenance) {
        if (maintenance == null) {
            return null;
        }
        ResponseMaintenanceDTO responseMaintenanceDTO = new ResponseMaintenanceDTO();
        responseMaintenanceDTO.setId(maintenance.getId());
        responseMaintenanceDTO.setServiceType(maintenance.getServiceType());
        responseMaintenanceDTO.setScheduledDate(maintenance.getScheduledDate());
        responseMaintenanceDTO.setCarId(maintenance.getCar().getId());
        responseMaintenanceDTO.setCarName(maintenance.getCar().getMake() + " " + maintenance.getCar().getModel());
        responseMaintenanceDTO.setGarageId(maintenance.getGarage().getId());
        responseMaintenanceDTO.setGarageName(maintenance.getGarage().getName());
        return responseMaintenanceDTO;
    }

}
