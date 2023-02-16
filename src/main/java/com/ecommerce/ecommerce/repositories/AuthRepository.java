package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.models.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, String> {
    Optional<Auth> findByEmail(String email);
}
