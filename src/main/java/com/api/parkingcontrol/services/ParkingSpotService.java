package com.api.parkingcontrol.services;

import com.api.parkingcontrol.domain.ParkingSpot;
import com.api.parkingcontrol.requests.ParkingSpotGet;
import com.api.parkingcontrol.requests.ParkingSpotPostRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ParkingSpotService {

    UUID save(ParkingSpot parkingSpot);
    ParkingSpot findByIdOrThrowNotFoundException(UUID id);
    ParkingSpot findByParkingSpotNumberOrThrowNotFoundException(String parkingSpotNumber);
    Page<ParkingSpotGet> findAllPageable(Pageable pageable);
    void addCarInTheParkingSpotByLicensePlate(String parkingSpotNumber, String licensePlate);
    void deleteById(UUID id);
    void removeCarFromParkingSpotByParkingSpotNumber(String parkingSpotNumber);
    void updateById(UUID id, ParkingSpotPostRequestBody parkingSpotPostRequestBody);
}