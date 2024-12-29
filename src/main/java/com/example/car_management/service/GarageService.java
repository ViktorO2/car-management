package com.example.car_management.service;

import com.example.car_management.dto.Create.CreateGarageDTO;
import com.example.car_management.dto.GarageDailyAvailabilityReportDTO;
import com.example.car_management.dto.Response.ResponseGarageDTO;
import com.example.car_management.dto.Update.UpdateGarageDTO;

import java.time.LocalDate;
import java.util.List;

public interface GarageService {

    List<ResponseGarageDTO> getAllGarages(String city);
    ResponseGarageDTO getGarageById(Long id);
    ResponseGarageDTO createGarage(CreateGarageDTO createGarageDTO);
    ResponseGarageDTO updateGarage(Long id, UpdateGarageDTO updateGarageDTO);
    void deleteGarage(Long id);

    List<GarageDailyAvailabilityReportDTO> getGarageDailyReport(Long garageId, LocalDate startDate, LocalDate endDate);
}