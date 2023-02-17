package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndStatus(String email, int status);
}
