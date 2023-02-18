package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndStatus(Long id, int status);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndStatus(String email, int status);
}
