package com.api.parkingcontrol.services;

import com.api.parkingcontrol.domain.User;
import com.api.parkingcontrol.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface UserService {

    UUID save(User user);

    User findById(UUID id);

    User findByUsername(String username);
}
