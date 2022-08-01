package com.api.parkingcontrol.services;

import com.api.parkingcontrol.domain.User;

import java.util.UUID;

public interface UserService {

    UUID save(User user);

    User findByUsernameOrThrowNotFoundException(String username);

    User findByIdOrThrowNotFoundException(UUID id);
}
