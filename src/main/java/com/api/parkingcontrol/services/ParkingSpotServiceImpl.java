package com.api.parkingcontrol.services;

import com.api.parkingcontrol.domain.Car;
import com.api.parkingcontrol.domain.ParkingSpot;
import com.api.parkingcontrol.exceptions.AlreadyExistsException;
import com.api.parkingcontrol.exceptions.NotFoundException;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final CarService carService;

    public ParkingSpotServiceImpl(ParkingSpotRepository parkingSpotRepository, CarService carService) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.carService = carService;
    }

    @Override
    @Transactional
    public UUID save(ParkingSpot parkingSpot) {
        if (parkingSpotRepository.existsByParkingSpotNumber(parkingSpot.getParkingSpotNumber())) {
            throw new AlreadyExistsException("Already Exists a ParkingSpot with ParkingSpotNumber `%s`"
                    .formatted(parkingSpot.getParkingSpotNumber()));
        }
        if (parkingSpotRepository.existsByApartmentAndBlock(parkingSpot.getApartment(), parkingSpot.getBlock())) {
            throw new AlreadyExistsException("Already Exists a ParkingSpot registered for apartment `%s` and block `%s`"
                    .formatted(parkingSpot.getApartment(), parkingSpot.getBlock()));
        }
        return parkingSpotRepository.save(parkingSpot).getId();
    }

    @Override
    public ParkingSpot findByIdOrThrowNotFoundException(UUID id) {
        return parkingSpotRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found ParkingSpot with Id `%s`".formatted(id)));
    }

    @Override
    public ParkingSpot findByParkingSpotNumberOrThrowNotFoundException(String parkingSpotNumber) {
        return parkingSpotRepository.findByParkingSpotNumber(parkingSpotNumber)
                .orElseThrow(() -> new NotFoundException("Not Found ParkingSpot with parkingSpotNumber `%s`".formatted(parkingSpotNumber)));
    }

    @Override
    @Transactional
    public void addCarInTheParkingSpotByLicensePlate(String parkingSpotNumber, String licensePlate) {
        ParkingSpot possibleParkingSpot = findByParkingSpotNumberOrThrowNotFoundException(parkingSpotNumber);
        Car possibleCar = carService.findByLicensePlateOrThrowNotFoundException(licensePlate);

        if (possibleParkingSpot.getAvailable().equals(false) && possibleParkingSpot.getCar() != null) {
            throw new AlreadyExistsException("Already Exists a Car in parkingSpot with parkingSpotNumber `%s` and licensePlate `%s`"
                    .formatted(parkingSpotNumber, possibleParkingSpot.getCar().getLicensePlate()));
        }
        if (possibleCar.getParkingSpot() != null) {
            throw new AlreadyExistsException("Already Exists ParkingSpot for licensePlate `%s`".formatted(licensePlate));
        }

        possibleCar.setParkingSpot(possibleParkingSpot);
        possibleParkingSpot.setCar(possibleCar);
        possibleParkingSpot.setAvailable(false);
        parkingSpotRepository.save(possibleParkingSpot);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        ParkingSpot possibleParkingSpot = findByIdOrThrowNotFoundException(id);
        possibleParkingSpot.getCar().setParkingSpot(null);
        parkingSpotRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void removeCarFromParkingSpotByParkingSpotNumber(String parkingSpotNumber) {
        ParkingSpot possibleParkingSpot = findByParkingSpotNumberOrThrowNotFoundException(parkingSpotNumber);
        if (possibleParkingSpot.getCar() != null) {
            possibleParkingSpot.getCar().setParkingSpot(null);
            possibleParkingSpot.setCar(null);
            possibleParkingSpot.setAvailable(true);
            parkingSpotRepository.save(possibleParkingSpot);
        }
    }
}