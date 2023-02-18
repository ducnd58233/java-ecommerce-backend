package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.common.CustomStatus;
import com.ecommerce.ecommerce.securities.exception.ApiRequestException;
import com.ecommerce.ecommerce.controllers.request.AuthenticationRequest;
import com.ecommerce.ecommerce.controllers.request.RegisterRequest;
import com.ecommerce.ecommerce.controllers.response.AuthenticationResponse;
import com.ecommerce.ecommerce.common.Role;
import com.ecommerce.ecommerce.models.User;
import com.ecommerce.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("authenticationService")
@RequiredArgsConstructor
public class AuthenticationService {
    private static final Logger log = LogManager.getLogger(AuthenticationService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        log.debug("Entering register service");
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.error("Email already exists");
            throw new ApiRequestException("email already exists", HttpStatus.CONFLICT);
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .phone(request.getPhone())
                .birthday(request.getBirthday())
                .build();
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        log.info("User created successfully");
        return response;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        log.debug("Entering login service");

        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user == null || (user != null && user.getStatus() == CustomStatus.INACTIVE.getValue())) {
            log.error("User not found");
            throw new ApiRequestException("email or password is incorrect", HttpStatus.NOT_FOUND);
        }

        if (user != null && user.getStatus() == CustomStatus.BANNED.getValue()) {
            log.error("User has been banned");
            throw new ApiRequestException("user has been banned", HttpStatus.FORBIDDEN);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        log.info("User login successfully");
        return response;
    }
}
