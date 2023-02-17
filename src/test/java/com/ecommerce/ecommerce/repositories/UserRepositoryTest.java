package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.common.Role;
import com.ecommerce.ecommerce.common.Status;
import com.ecommerce.ecommerce.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User userActive;
    private User userInactive;

    @BeforeEach
    void setUp() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // User 1 - active
        String emailUserActive = "duc1@gmail.com";
        String passwordUserActive = "123456";
        String encodedPasswordUserActive = encoder.encode(passwordUserActive);
        String firstNameUserActive  = "duc";
        String lastNameUserActive  = "nguyen";
        Calendar birthDayUserActive  = new GregorianCalendar(2000, 11, 14);
        String phoneUserActive = "01234567891";
        userActive = User.builder()
                .email(emailUserActive)
                .password(encodedPasswordUserActive)
                .firstname(firstNameUserActive )
                .lastname(lastNameUserActive )
                .birthday(Date.valueOf(sdf.format(birthDayUserActive .getTime())))
                .phone(phoneUserActive)
                .role(Role.USER)
                .build();
        userRepository.save(userActive);

        // User 2 - inactive
        String emailUserInactive = "duc2@gmail.com";
        String passwordUserInactive = "123456";
        String encodedPasswordUserInactive = encoder.encode(passwordUserInactive);
        String firstNameUserInactive  = "duc";
        String lastNameUserInactive  = "nguyen";
        Calendar birthDayUserInactive  = new GregorianCalendar(2000, 11, 14);
        String phoneUserInactive = "01234567891";
        userInactive = User.builder()
                .email(emailUserInactive)
                .password(encodedPasswordUserInactive)
                .firstname(firstNameUserInactive)
                .lastname(lastNameUserInactive)
                .birthday(Date.valueOf(sdf.format(birthDayUserInactive .getTime())))
                .phone(phoneUserInactive)
                .role(Role.USER)
                .build();
        userInactive.setStatus(Status.INACTIVE.getValue());
        userRepository.save(userInactive);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfEmailExistsAndStillActive() {
        // give
        String email = "duc1@gmail.com";
        // when
        User actual = userRepository.findByEmailAndStatus(email, Status.ACTIVE.getValue()).orElse(null);
        // then
        assertEquals(userActive, actual);
    }

    @Test
    void itShouldCheckIfEmailExistsButInactive() {
        // give
        String email = "duc2@gmail.com";
        // when
        User actual = userRepository.findByEmailAndStatus(email, Status.INACTIVE.getValue()).orElse(null);
        // then
        assertEquals(userInactive, actual);
    }
}