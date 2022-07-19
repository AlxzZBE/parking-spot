package com.api.parkingcontrol.requests;

import com.api.parkingcontrol.domain.ParkingSpot;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ParkingSpotPostRequestBody {

    @NotBlank
    private String parkingSpotNumber;
    @NotBlank
    private String apartment;
    @NotBlank
    private String block;

    public ParkingSpot newParkingSpot() {
        ParkingSpot newParkingSpot = new ParkingSpot();
        newParkingSpot.setParkingSpotNumber(this.parkingSpotNumber);
        newParkingSpot.setApartment(this.apartment);
        newParkingSpot.setBlock(this.block);
        newParkingSpot.setAvailable(true);
        newParkingSpot.setRegistrationDate(LocalDateTime.now());
        return newParkingSpot;
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
}