package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.requests.ParkingSpotGet;
import com.api.parkingcontrol.requests.ParkingSpotPostRequestBody;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/parking-spot")
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid ParkingSpotPostRequestBody parkingSpotPostRequestBody) {
        UUID parkingSavedId = parkingSpotService.save(parkingSpotPostRequestBody.newParkingSpot());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", parkingSavedId).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping(params = {"parkingSpotNumber", "licensePlate"})
    public ResponseEntity<Void> addCarInTheParkingSpotByLicensePlate(@RequestParam String parkingSpotNumber,
                                                                     @RequestParam String licensePlate) {
        parkingSpotService.addCarInTheParkingSpotByLicensePlate(parkingSpotNumber, licensePlate);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(params = "id")
    public ResponseEntity<ParkingSpotGet> findById(@RequestParam UUID id) {
        return ResponseEntity.ok(new ParkingSpotGet(parkingSpotService.findByIdOrThrowNotFoundException(id)));
    }

    @GetMapping(params = "parkingSpotNumber")
    public ResponseEntity<ParkingSpotGet> findByParkingSpotNumber(@RequestParam String parkingSpotNumber) {
        return ResponseEntity.ok(new ParkingSpotGet(parkingSpotService.findByParkingSpotNumberOrThrowNotFoundException(parkingSpotNumber)));
    }
}