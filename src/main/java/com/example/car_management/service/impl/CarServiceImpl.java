package com.example.car_management.service.impl;

import com.example.car_management.dto.CarDTO;
import com.example.car_management.entity.Car;
import com.example.car_management.repository.CarRepository;
import com.example.car_management.repository.GarageRepository;
import com.example.car_management.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final GarageRepository garageRepository;
    private final ModelMapper modelMapper;
    public CarServiceImpl(CarRepository carRepository, GarageRepository garageRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.garageRepository = garageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CarDTO> getAllCars(String make, String model, Integer startYear, Integer endYear) {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public CarDTO createCar(CarDTO carDTO) {
        Car car=modelMapper.map(carDTO,Car.class);
        car=carRepository.save(car);
        return modelMapper.map(car,CarDTO.class);
    }
}
