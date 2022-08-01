package com.api.parkingcontrol.services;

import com.api.parkingcontrol.domain.Car;
import com.api.parkingcontrol.exceptions.AlreadyExistsException;
import com.api.parkingcontrol.exceptions.NotFoundException;
import com.api.parkingcontrol.repositories.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public UUID save(Car car) {
        boolean possibleCar = carRepository.findByLicensePlate(car.getLicensePlate()).isPresent();
        if (possibleCar) {
            throw new AlreadyExistsException("Already Exists a Car with licensePlate `%s`"
                    .formatted(car.getLicensePlate()));
        }
        return carRepository.save(car).getId();
    }

    @Override
    public Car findByIdOrThrowNotFoundException(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Car with id `%s`".formatted(id)));
    }

    @Override
    public Car findByLicensePlateOrThrowNotFoundException(String licensePlate) {
        return carRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new NotFoundException("Not Found Car with licensePlate `%s`".formatted(licensePlate)));
    }

    @Override
    public Page<Car> findAllPageable(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        Car possibleCar = findByIdOrThrowNotFoundException(id);
        if (possibleCar.getParkingSpot() != null)
            possibleCar.getParkingSpot().setCar(null);
        carRepository.deleteById(id);
    }
}