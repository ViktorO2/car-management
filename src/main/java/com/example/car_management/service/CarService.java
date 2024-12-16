package com.example.car_management.service;

import com.example.car_management.dto.CreateCarDTO;
import com.example.car_management.dto.ResponseCarDTO;
import com.example.car_management.dto.UpdateCarDTO;
import com.example.car_management.dto.UpdateGarageDTO;

import java.util.List;

public interface CarService {
   ResponseCarDTO createCar(CreateCarDTO createCarDTO);
   List<ResponseCarDTO> getAllCars(String carMake,Long garageId,Integer fromYear,Integer toYear);
   ResponseCarDTO getCarById(Long id);
   ResponseCarDTO updateCar(Long id, UpdateCarDTO updateCarDTO);
   void deleteCar(Long id);
}
