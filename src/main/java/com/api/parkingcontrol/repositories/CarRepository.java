package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

    Optional<Car> findByLicensePlate(String licensePlate);
}
