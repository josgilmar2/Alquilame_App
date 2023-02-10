package com.salesianostriana.dam.alquilame.user.repo;

import com.salesianostriana.dam.alquilame.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByUsername(String username);

}
