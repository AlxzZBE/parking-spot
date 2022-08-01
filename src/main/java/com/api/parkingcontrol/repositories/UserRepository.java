package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
