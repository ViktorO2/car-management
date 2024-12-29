package com.example.car_management.service.impl;

import com.example.car_management.dto.Create.CreateGarageDTO;
import com.example.car_management.dto.GarageDailyAvailabilityReportDTO;
import com.example.car_management.dto.Response.ResponseGarageDTO;
import com.example.car_management.dto.Update.UpdateGarageDTO;
import com.example.car_management.entity.Garage;
import com.example.car_management.repository.GarageRepository;
import com.example.car_management.repository.MaintenanceRepository;
import com.example.car_management.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GarageServiceImpl implements GarageService {
    @Autowired
    private final GarageRepository garageRepository;
    private final MaintenanceRepository maintenanceRepository;
    public GarageServiceImpl(GarageRepository garageRepository, MaintenanceRepository maintenanceRepository) {
        this.garageRepository = garageRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    private Garage mapCreateGarageDTOToGarage(CreateGarageDTO createGarageDTO) {
      if(createGarageDTO==null){
          return null;
      }
        Garage garage = new Garage();
        garage.setName(createGarageDTO.getName());
        garage.setLocation(createGarageDTO.getLocation());
        garage.setCity(createGarageDTO.getCity());
        garage.setCapacity(createGarageDTO.getCapacity());
        return garage;
    }

    private ResponseGarageDTO mapGarageToResponseGarageDTO(Garage garage) {
        if(garage==null){
            return null;
        }
        ResponseGarageDTO responseGarageDTO = new ResponseGarageDTO();
        responseGarageDTO.setId(garage.getId());
        responseGarageDTO.setName(garage.getName());
        responseGarageDTO.setCity(garage.getCity());
        responseGarageDTO.setLocation(garage.getLocation());
        responseGarageDTO.setCapacity(garage.getCapacity());
        return responseGarageDTO;
    }


    @Override
    public ResponseGarageDTO createGarage(CreateGarageDTO createGarageDTO) {
        Garage garage = mapCreateGarageDTOToGarage(createGarageDTO);
        garage = garageRepository.save(garage);
        return mapGarageToResponseGarageDTO(garage);
    }

    @Override
    public List<ResponseGarageDTO> getAllGarages(String city) {
        List<Garage> garages = (city == null) ? garageRepository.findAll() : garageRepository.findByCity(city);
        return garages.stream()
                .map(this::mapGarageToResponseGarageDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseGarageDTO getGarageById(Long id) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found"));
        return mapGarageToResponseGarageDTO(garage);
    }

    @Override
    public ResponseGarageDTO updateGarage(Long id, UpdateGarageDTO updateGarageDTO) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found"));

        garage.setName(updateGarageDTO.getName());
        garage.setLocation(updateGarageDTO.getLocation());
        garage.setCity(updateGarageDTO.getCity());
        garage.setCapacity(updateGarageDTO.getCapacity());

        garage = garageRepository.save(garage);
        return mapGarageToResponseGarageDTO(garage);
    }

    @Override
    public void deleteGarage(Long id) {
        if (!garageRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found");
        }
        garageRepository.deleteById(id);
    }

    @Override
    public List<GarageDailyAvailabilityReportDTO> getGarageDailyReport(Long garageId, LocalDate startDate, LocalDate endDate) {
        List<Object[]> rawResults = garageRepository.getGarageDailyAvailability(garageId, startDate, endDate);
        return rawResults.stream()
                .map(row -> {
                    try {
                        Date sqlDate = (Date) row[0];
                        LocalDate date = LocalDate.parse(sqlDate.toString());

                        Integer requests = ((Number) row[1]).intValue();
                        Integer availableCapacity = ((Number) row[2]).intValue();

                        return new GarageDailyAvailabilityReportDTO(date, requests, availableCapacity);
                    } catch (Exception e) {
                        System.err.println("Error mapping row: " + Arrays.toString(row));
                        e.printStackTrace();
                        return null;
                    }

                })
                .collect(Collectors.toList());
    }

}
