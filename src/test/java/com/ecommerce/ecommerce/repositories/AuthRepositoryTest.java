package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.common.Role;
import com.ecommerce.ecommerce.common.Status;
import com.ecommerce.ecommerce.models.Auth;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AuthRepositoryTest {
    @Autowired
    private AuthRepository authRepository;
    private Auth userActive;
    private Auth userInactive;

    @BeforeEach
    void setUp() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // User 1 - active
        String emailUserActive = "duc1@gmail.com";
        String passwordUserActive = "123456";
        String encodedPasswordUserActive = encoder.encode(passwordUserActive);
        userActive = Auth.builder()
                .email(emailUserActive)
                .password(encodedPasswordUserActive)
                .status(Status.ACTIVE.getValue())
                .role(Role.USER)
                .build();
        authRepository.save(userActive);

        // User 2 - inactive
        String emailUserInactive = "duc2@gmail.com";
        String passwordUserInactive = "123456";
        String encodedPasswordUserInactive = encoder.encode(passwordUserInactive);
        userInactive = Auth.builder()
                .email(emailUserInactive)
                .password(encodedPasswordUserInactive)
                .status(Status.INACTIVE.getValue())
                .role(Role.USER)
                .build();
        authRepository.save(userInactive);
    }

    @AfterEach
    void tearDown() {
        authRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfEmailExistsAndStillActive() {
        // give
        String email = "duc1@gmail.com";
        // when
        Auth actual = authRepository.findByEmailAndStatus(email, Status.ACTIVE.getValue()).orElse(null);
        // then
        assertEquals(userActive, actual);
    }

    @Test
    void itShouldCheckIfEmailExistsButInactive() {
        // give
        String email = "duc2@gmail.com";
        // when
        Auth actual = authRepository.findByEmailAndStatus(email, Status.INACTIVE.getValue()).orElse(null);
        // then
        assertEquals(userInactive, actual);
    }
}