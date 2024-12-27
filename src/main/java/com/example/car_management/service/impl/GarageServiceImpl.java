package com.example.car_management.service.impl;

import com.example.car_management.dto.Create.CreateGarageDTO;
import com.example.car_management.dto.GarageDailyAvailabilityReportDTO;
import com.example.car_management.dto.Response.ResponseGarageDTO;
import com.example.car_management.dto.Update.UpdateGarageDTO;
import com.example.car_management.entity.Garage;
import com.example.car_management.repository.GarageRepository;
import com.example.car_management.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GarageServiceImpl implements GarageService {
    @Autowired
    private final GarageRepository garageRepository;

    public GarageServiceImpl(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    private Garage mapCreateGarageDTOToGarage(CreateGarageDTO createGarageDTO) {
        Garage garage = new Garage();
        garage.setName(createGarageDTO.getName());
        garage.setLocation(createGarageDTO.getLocation());
        garage.setCity(createGarageDTO.getCity());
        garage.setCapacity(createGarageDTO.getCapacity());
        return garage;
    }

    private ResponseGarageDTO mapGarageToResponseGarageDTO(Garage garage) {
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
    public List<GarageDailyAvailabilityReportDTO> getAvailabilityReport(Long garageId, LocalDate startDate, LocalDate endDate) {
        // Извличане на всички заявки за даден сервиз в зададения диапазон от дати
        List<Garage> garages = garageRepository.findAll(); // Може да добавите филтър за конкретния сервиз, ако е необходимо

        // Създаваме примерна статистика за заявките на сервиза
        return garages.stream()
                .filter(garage -> garage.getId().equals(garageId)) // Филтрираме по сервиз
                .map(garage -> {
                    int totalCapacity = garage.getCapacity();
                    List<LocalDate> datesInRange = getDatesBetween(startDate, endDate); // Получаваме всички дати в диапазона
                    return datesInRange.stream()
                            .map(date -> {
                                int requests = getRequestsForDate(garageId, date); // Тук ще броим заявките за конкретната дата
                                int availableCapacity = totalCapacity - requests; // Изчисляваме свободния капацитет
                                return new GarageDailyAvailabilityReportDTO(date, requests, availableCapacity);
                            })
                            .collect(Collectors.toList());
                })
                .flatMap(List::stream) // Разгъваме списъците в един
                .collect(Collectors.toList());
    }

    // Метод за извличане на заявките за даден сервиз за конкретна дата
    private int getRequestsForDate(Long garageId, LocalDate date) {
        // Тук ще добавите логиката за броене на заявките за даден сервиз и дата
        // Примерно броене на заявки за съответната дата
        return 0; // Поставете правилния код тук
    }

    // Метод за извличане на всички дати между две дати
    private List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
    }
}
