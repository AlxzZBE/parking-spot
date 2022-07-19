package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.domain.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, UUID> {
    Optional<ParkingSpot> findByParkingSpotNumber(String parkingSpotNumber);

    boolean existsByParkingSpotNumber(String parkingSpotNumber);

    boolean existsByApartmentAndBlock(String apartment, String block);
}
