package com.api.parkingcontrol.services;

import com.api.parkingcontrol.domain.Car;

import java.util.UUID;

public interface CarService {

    UUID save(Car car);

    Car findByIdOrThrowNotFoundException(UUID id);

    Car findByLicensePlateOrThrowNotFoundException(String licensePlate);
}
