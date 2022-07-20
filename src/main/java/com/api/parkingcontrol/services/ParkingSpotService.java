package com.api.parkingcontrol.services;

import com.api.parkingcontrol.domain.ParkingSpot;
import com.api.parkingcontrol.requests.ParkingSpotPostRequestBody;

import java.util.UUID;

public interface ParkingSpotService {

    UUID save(ParkingSpot parkingSpot);
    ParkingSpot findByIdOrThrowNotFoundException(UUID id);
    ParkingSpot findByParkingSpotNumberOrThrowNotFoundException(String parkingSpotNumber);
    void addCarInTheParkingSpotByLicensePlate(String parkingSpotNumber, String licensePlate);
    void deleteById(UUID id);
    void removeCarFromParkingSpotByParkingSpotNumber(String parkingSpotNumber);
    void updateById(UUID id, ParkingSpotPostRequestBody parkingSpotPostRequestBody);
}