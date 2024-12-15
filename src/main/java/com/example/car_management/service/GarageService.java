package com.example.car_management.service;

import com.example.car_management.dto.GarageDTO;
import com.example.car_management.entity.Garage;
import com.example.car_management.repository.GarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GarageService {

    List<GarageDTO> getAllGarages(String city);
    GarageDTO getGarageById(Long id);
    GarageDTO createGarage(GarageDTO garageDTO);
    GarageDTO updateGarage(Long id, GarageDTO garageDTO);
    void deleteGarage(Long id);
    }
