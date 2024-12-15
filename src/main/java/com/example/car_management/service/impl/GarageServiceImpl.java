package com.example.car_management.service.impl;

import com.example.car_management.dto.GarageDTO;
import com.example.car_management.entity.Garage;
import com.example.car_management.repository.GarageRepository;
import com.example.car_management.service.GarageService;
import org.modelmapper.ModelMapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GarageServiceImpl implements GarageService {

    private final GarageRepository garageRepository;
    private final ModelMapper modelMapper;

    public GarageServiceImpl(GarageRepository garageRepository, ModelMapper modelMapper) {
        this.garageRepository = garageRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<GarageDTO> getAllGarages(String city) {
        List<Garage> garages = (city == null) ?
                garageRepository.findAll() : garageRepository.findByCity(city);

        return garages.stream()
                .map(garage -> modelMapper.map(garage, GarageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GarageDTO getGarageById(Long id) {
        Optional<Garage> garage=garageRepository.findById(id);
        if (garage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found");
        }else{
        return modelMapper.map(garage,GarageDTO.class);
        }
    }

    @Override
    public GarageDTO createGarage(GarageDTO garageDTO) {
        Garage garage = modelMapper.map(garageDTO, Garage.class);
        garage = garageRepository.save(garage);
        return modelMapper.map(garage, GarageDTO.class);
    }

    @Override
    public GarageDTO updateGarage(Long id, GarageDTO garageDTO) {
        Optional<Garage> existedGarage=garageRepository.findById(id);
        if (existedGarage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found");
        }else {
            Garage garage=existedGarage.get();
            garage.setName(garageDTO.getName());
            garage.setLocation(garageDTO.getLocation());
            garage.setCity(garageDTO.getCity());
            garage.setCapacity(garageDTO.getCapacity());
            garage=this.garageRepository.save(garage);
            return this.modelMapper.map(garage, GarageDTO.class);
        }

    }

    public void deleteGarage(Long id) {
        if (!garageRepository.existsById(id)) {
            throw new RuntimeException("Garage not found");
        }
        garageRepository.deleteById(id);
    }
}

