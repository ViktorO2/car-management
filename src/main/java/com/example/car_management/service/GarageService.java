package com.example.car_management.service;

import com.example.car_management.dto.CreateGarageDTO;
import com.example.car_management.dto.ResponseGarageDTO;
import com.example.car_management.dto.UpdateGarageDTO;

import java.util.List;

public interface GarageService {

    List<ResponseGarageDTO> getAllGarages(String city);
    ResponseGarageDTO getGarageById(Long id);
    ResponseGarageDTO createGarage(CreateGarageDTO createGarageDTO);
    ResponseGarageDTO updateGarage(Long id, UpdateGarageDTO updateGarageDTO);
    void deleteGarage(Long id);
    }
