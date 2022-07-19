package com.api.parkingcontrol.services;

import com.api.parkingcontrol.domain.ParkingSpot;

import java.util.UUID;

public interface ParkingSpotService {

    UUID save(ParkingSpot parkingSpot);
    ParkingSpot findByIdOrThrowNotFoundException(UUID id);
    ParkingSpot findByParkingSpotNumberOrThrowNotFoundException(String parkingSpotNumber);
    void addCarInTheParkingSpotByLicensePlate(String parkingSpotNumber, String licensePlate);
}