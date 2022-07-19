package com.api.parkingcontrol.requests;

import com.api.parkingcontrol.domain.ParkingSpot;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingSpotGet {

    private UUID id;
    private LocalDateTime registrationDate;
    private String parkingSpotNumber;
    private String apartment;
    private String block;
    private Boolean available;
    private CarGet car;

    public ParkingSpotGet(ParkingSpot parkingSpot) {
        this.id = parkingSpot.getId();
        this.registrationDate = parkingSpot.getRegistrationDate();
        this.parkingSpotNumber = parkingSpot.getParkingSpotNumber();
        this.apartment = parkingSpot.getApartment();
        this.block = parkingSpot.getBlock();
        this.available = parkingSpot.getAvailable();
        if (parkingSpot.getCar() != null) {
            this.car = new CarGet(parkingSpot.getCar());
        }
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public String getApartment() {
        return apartment;
    }

    public String getBlock() {
        return block;
    }

    public Boolean getAvailable() {
        return available;
    }

    public CarGet getCar() {
        return car;
    }
}
