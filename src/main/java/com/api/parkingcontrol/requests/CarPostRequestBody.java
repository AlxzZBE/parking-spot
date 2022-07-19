package com.api.parkingcontrol.requests;

import com.api.parkingcontrol.domain.Car;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CarPostRequestBody {

    @NotBlank
    @Length(min = 3)
    private String responsibleName;

    @NotBlank
    private LocalDateTime registrationDate;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String color;

    @NotBlank
    @Length(max = 7)
    private String licensePlate;

    public Car newCar() {
        Car newCar = new Car();
        newCar.setResponsibleName(this.responsibleName);
        newCar.setBrand(this.brand);
        newCar.setModel(this.model);
        newCar.setColor(this.color);
        newCar.setLicensePlate(this.licensePlate);
        newCar.setRegistrationDate(LocalDateTime.now());
        return newCar;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
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
}