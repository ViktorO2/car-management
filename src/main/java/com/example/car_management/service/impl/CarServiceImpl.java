package com.example.car_management.service.impl;

import com.example.car_management.dto.*;
import com.example.car_management.entity.Car;
import com.example.car_management.entity.Garage;
import com.example.car_management.repository.CarRepository;
import com.example.car_management.repository.GarageRepository;
import com.example.car_management.service.CarService;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, GarageRepository garageRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.garageRepository = garageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseCarDTO createCar(CreateCarDTO createCarDTO) {
        Car car = modelMapper.map(createCarDTO, Car.class);
        List<Garage> garages = garageRepository.findAllById(createCarDTO.getGarageIds());
        car.setGarages(garages);
        car = carRepository.save(car);
        return modelMapper.map(car, ResponseCarDTO.class);
    }
    @Override
    public List<ResponseCarDTO> getAllCars(String carMake, Long garageId, Integer fromYear, Integer toYear) {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .filter(car -> (carMake == null || car.getMake().equalsIgnoreCase(carMake)))
                .filter(car -> (garageId == null || car.getGarages().stream().allMatch(g -> g.getId().equals(garageId))))
                .filter(car -> (fromYear == null || car.getProductionYear() >= fromYear))
                .filter(car -> (toYear == null || car.getProductionYear() <= toYear))
                .map(car -> modelMapper.map(car, ResponseCarDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public ResponseCarDTO getCarById(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));

        ResponseCarDTO carDTO= modelMapper.map(car, ResponseCarDTO.class);

        List<ResponseGarageDTO> garageDTOS=car.getGarages().stream()
                .map(garage -> modelMapper.map(garage,ResponseGarageDTO.class))
                .collect(Collectors.toList());
        carDTO.setGarages(garageDTOS);

        return carDTO;
    }

    @Override
    public ResponseCarDTO updateCar(Long id, UpdateCarDTO updateCarDTO) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not Found"));
        car.setMake(updateCarDTO.getMake());
        car.setModel(updateCarDTO.getModel());
        car.setProductionYear(updateCarDTO.getProductionYear());
        car.setLicensePlate(updateCarDTO.getLicensePlate());
        List<Garage> garages = garageRepository.findAllById(updateCarDTO.getGarageIds());
        car.setGarages(garages);
        car = carRepository.save(car);
        return modelMapper.map(car, ResponseCarDTO.class);

    }

    @Override
    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }
        carRepository.deleteById(id);
    }


}
