package com.example.car_management.service.impl;

import com.example.car_management.dto.Create.CreateCarDTO;
import com.example.car_management.dto.Response.ResponseCarDTO;
import com.example.car_management.dto.Response.ResponseGarageDTO;
import com.example.car_management.dto.Update.UpdateCarDTO;
import com.example.car_management.entity.Car;
import com.example.car_management.entity.Garage;
import com.example.car_management.repository.CarRepository;
import com.example.car_management.repository.GarageRepository;
import com.example.car_management.service.CarService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private final CarRepository carRepository;
    @Autowired
    private final GarageRepository garageRepository;

    public CarServiceImpl(CarRepository carRepository, GarageRepository garageRepository) {
        this.carRepository = carRepository;
        this.garageRepository = garageRepository;
    }


    private Car mapCreateCarDTOToCar(CreateCarDTO createCarDTO) {
        if (createCarDTO == null) {
            return null;
        }
        Car car = new Car();
        car.setMake(createCarDTO.getMake());
        car.setModel(createCarDTO.getModel());
        car.setProductionYear(createCarDTO.getProductionYear());
        car.setLicensePlate(createCarDTO.getLicensePlate());

        List<Garage> garages = garageRepository.findAllById(createCarDTO.getGarageIds());
        garages.forEach(garage -> garage.addCar(car));
        car.setGarages(garages);

        return car;
    }

    private ResponseCarDTO mapCarToResponseCarDTO(Car car) {
        if (car == null) {
            return null;
        }

        ResponseCarDTO responseCarDTO = new ResponseCarDTO();
        responseCarDTO.setId(car.getId());
        responseCarDTO.setMake(car.getMake());
        responseCarDTO.setModel(car.getModel());
        responseCarDTO.setProductionYear(car.getProductionYear());
        responseCarDTO.setLicensePlate(car.getLicensePlate());

        // Мапваме гаражите към DTO
        List<ResponseGarageDTO> garageDTOs = car.getGarages().stream()
                .map(garage -> new ResponseGarageDTO(
                        garage.getId(),
                        garage.getName(),
                        garage.getCity(),
                        garage.getLocation(),
                        garage.getCapacity(),
                        null // Пропускаме списъка с коли, за да избегнем рекурсия
                ))
                .collect(Collectors.toList());

        responseCarDTO.setGarages(garageDTOs);
        return responseCarDTO;
    }

    @Override
    @Transactional
    public ResponseCarDTO createCar(CreateCarDTO createCarDTO) {
        if (createCarDTO.getMake().isBlank() || createCarDTO.getModel().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data");
        }
        Car car = mapCreateCarDTOToCar(createCarDTO);
        Car savedCar=carRepository.save(car);
        return mapCarToResponseCarDTO(savedCar);
    }

    @Override
    public List<ResponseCarDTO> getAllCars(String carMake, Long garageId, Integer fromYear, Integer toYear) {
        List<Car> cars = carRepository.findCarsByFilters(carMake,garageId,fromYear,toYear);
        if(cars.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }
        return cars.stream()
                .map(this::mapCarToResponseCarDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseCarDTO getCarById(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        return mapCarToResponseCarDTO(car);
    }

    @Override
    public ResponseCarDTO updateCar(Long id, UpdateCarDTO updateCarDTO) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));

        car.setMake(updateCarDTO.getMake());
        car.setModel(updateCarDTO.getModel());
        car.setProductionYear(updateCarDTO.getProductionYear());
        car.setLicensePlate(updateCarDTO.getLicensePlate());
        List<Garage> garages = garageRepository.findAllById(updateCarDTO.getGarageIds());
        car.setGarages(garages);

        car = carRepository.save(car);
        return mapCarToResponseCarDTO(car);
    }

    @Override
    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }
        carRepository.deleteById(id);
    }

}
