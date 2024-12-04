package com.reTheard.reThreard.repository;

import com.reTheard.reThreard.model.User;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email); 
    Optional<User> findByUsername(String username);
}
