package com.example.car_management.service.impl;

import com.example.car_management.dto.CreateGarageDTO;
import com.example.car_management.dto.ResponseGarageDTO;
import com.example.car_management.dto.UpdateGarageDTO;
import com.example.car_management.entity.Garage;
import com.example.car_management.repository.GarageRepository;
import com.example.car_management.service.GarageService;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GarageServiceImpl implements GarageService {
    @Autowired
    private final GarageRepository garageRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public GarageServiceImpl(GarageRepository garageRepository, ModelMapper modelMapper) {
        this.garageRepository = garageRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ResponseGarageDTO createGarage(CreateGarageDTO createGarageDTO) {
        Garage garage = modelMapper.map(createGarageDTO, Garage.class);
        garage = garageRepository.save(garage);
        return modelMapper.map(garage, ResponseGarageDTO.class);
    }

    @Override
    public List<ResponseGarageDTO> getAllGarages(String city) {
        List<Garage> garages = (city == null) ?
                garageRepository.findAll() : garageRepository.findByCity(city);

        return garages.stream()
                .map(garage -> modelMapper.map(garage, ResponseGarageDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public ResponseGarageDTO getGarageById(Long id) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found"));

        return modelMapper.map(garage, ResponseGarageDTO.class);
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
            return modelMapper.map(garage, ResponseGarageDTO.class);
        }
    public void deleteGarage(Long id) {
        if (!garageRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Garage not found");
        }
        garageRepository.deleteById(id);
    }
}

