package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.common.Status;
import com.ecommerce.ecommerce.common.exception.ApiRequestException;
import com.ecommerce.ecommerce.controllers.request.AuthenticationRequest;
import com.ecommerce.ecommerce.controllers.request.RegisterRequest;
import com.ecommerce.ecommerce.controllers.response.AuthenticationResponse;
import com.ecommerce.ecommerce.common.Role;
import com.ecommerce.ecommerce.models.Auth;
import com.ecommerce.ecommerce.models.User;
import com.ecommerce.ecommerce.repositories.AuthRepository;
import com.ecommerce.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (authRepository.findById(request.getEmail()).isPresent()) {
            throw new ApiRequestException("email already exists");
        }

        Auth auth = Auth.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        authRepository.save(auth);

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .phone(request.getPhone())
                .address(request.getAddress())
                .birthday(request.getBirthday())
                .build();
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(auth);
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

        return response;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Auth user = authRepository.findByEmailAndStatus(request.getEmail(), Status.ACTIVE.getValue())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

        return response;
    }
}
