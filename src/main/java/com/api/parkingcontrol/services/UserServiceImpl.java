package com.api.parkingcontrol.services;

import com.api.parkingcontrol.domain.User;
import com.api.parkingcontrol.exceptions.AlreadyExistsException;
import com.api.parkingcontrol.exceptions.NotFoundException;
import com.api.parkingcontrol.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UUID save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistsException("Already Exists a User with username `%s`".formatted(user.getUsername()));
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user).getId();
    }

    @Override
    public User findByIdOrThrowNotFoundException(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found User with id `%s`".formatted(id)));
    }

    @Override
    public User findByUsernameOrThrowNotFoundException(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Not Found User with username `%s`".formatted(username)));
    }
}
