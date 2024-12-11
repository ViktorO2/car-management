package com.example.car_management.service.impl;

import com.example.car_management.dto.GarageDTO;
import com.example.car_management.entity.Garage;
import com.example.car_management.repository.GarageRepository;
import com.example.car_management.service.GarageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarageServiceImpl implements GarageService {

    private final GarageRepository garageRepository;

    public GarageServiceImpl(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    @Override
    public List<GarageDTO> getAllGarages() {
        List<Garage> garages = garageRepository.findAll();
    return null;
    }
}
