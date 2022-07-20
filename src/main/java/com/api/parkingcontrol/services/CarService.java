package com.api.parkingcontrol.services;

import com.api.parkingcontrol.domain.Car;
import com.api.parkingcontrol.requests.CarGet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CarService {

    UUID save(Car car);
    Car findByIdOrThrowNotFoundException(UUID id);
    Car findByLicensePlateOrThrowNotFoundException(String licensePlate);
    Page<Car> findAllPageable(Pageable pageable);
}
