package com.api.parkingcontrol.requests;

import com.api.parkingcontrol.domain.Car;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarGet {

    private UUID id;
    private LocalDateTime registrationDate;
    private String responsibleName;
    private String brand;
    private String model;
    private String color;
    private String licensePlate;
    private String parkingSpotNumber;

    public CarGet(Car car) {
        this.id = car.getId();
        this.registrationDate = car.getRegistrationDate();
        this.responsibleName = car.getResponsibleName();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.color = car.getColor();
        this.licensePlate = car.getLicensePlate();
        if (car.getParkingSpot() != null) {
            this.parkingSpotNumber = car.getParkingSpot().getParkingSpotNumber();
        }
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }
}
