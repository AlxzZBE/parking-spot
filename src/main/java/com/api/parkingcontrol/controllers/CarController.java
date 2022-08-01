package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.requests.CarGet;
import com.api.parkingcontrol.requests.CarPostRequestBody;
import com.api.parkingcontrol.services.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CarPostRequestBody carPostRequestBody) {
        UUID carSavedId = carService.save(carPostRequestBody.newCar());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", carSavedId).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(params = "id")
    public ResponseEntity<CarGet> findByIdOrThrowNotFoundException(@RequestParam UUID id) {
        return ResponseEntity.ok(new CarGet(carService.findByIdOrThrowNotFoundException(id)));
    }

    @GetMapping(params = "licensePlate")
    public ResponseEntity<CarGet> findByLicensePlateOrThrowNotFoundException(@RequestParam String licensePlate) {
        return ResponseEntity.ok(new CarGet(carService.findByLicensePlateOrThrowNotFoundException(licensePlate)));
    }

    @GetMapping
    public ResponseEntity<Page<CarGet>> findAllPageable(Pageable pageable) {
        return ResponseEntity.ok(carService.findAllPageable(pageable).map(CarGet::new));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> deleteById(@RequestParam UUID id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}