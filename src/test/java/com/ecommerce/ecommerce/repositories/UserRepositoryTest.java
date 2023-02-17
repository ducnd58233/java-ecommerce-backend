package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // Connect to h2 database
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfUserIsSuccessfullyCreated() {
        // given
        String firstname = "duc";
        String lastname = "nguyen";
        String phone = "999999999";
        String address = "Go Vap";

        User user = User.builder()
                .firstname(firstname)
                .lastname(lastname)
                .phone(phone)
                .address(address)
                .build();

        userRepository.save(user);
        // when
        User userInDB = (User) userRepository.findById(1L).orElse(null);

        // then
        assertEquals(user, userInDB);
    }
}