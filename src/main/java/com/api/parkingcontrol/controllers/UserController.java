package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.requests.ParkingSpotGet;
import com.api.parkingcontrol.requests.UserGet;
import com.api.parkingcontrol.requests.UserPostRequestBody;
import com.api.parkingcontrol.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid UserPostRequestBody userPostRequestBody) {
        UUID userSavedId = userService.save(userPostRequestBody.newUser());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", userSavedId).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(params = "id")
    public ResponseEntity<UserGet> findByIdOrThrowNotFoundException(@RequestParam UUID id) {
        return ResponseEntity.ok(new UserGet(userService.findByIdOrThrowNotFoundException(id)));
    }
}