package com.example.car_management.service;

import com.example.car_management.dto.Create.CreateCarDTO;
import com.example.car_management.dto.Response.ResponseCarDTO;
import com.example.car_management.dto.Update.UpdateCarDTO;

import java.util.List;

public interface CarService {
   ResponseCarDTO createCar(CreateCarDTO createCarDTO);
   List<ResponseCarDTO> getAllCars(String carMake,Long garageId,Integer fromYear,Integer toYear);
   ResponseCarDTO getCarById(Long id);
   ResponseCarDTO updateCar(Long id, UpdateCarDTO updateCarDTO);
   void deleteCar(Long id);
}
