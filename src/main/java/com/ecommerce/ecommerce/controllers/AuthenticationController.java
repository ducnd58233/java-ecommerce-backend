package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.controllers.request.AuthenticationRequest;
import com.ecommerce.ecommerce.controllers.request.RegisterRequest;
import com.ecommerce.ecommerce.controllers.response.AuthenticationResponse;
import com.ecommerce.ecommerce.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private static final Logger log = LogManager.getLogger(AuthenticationController.class);

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        log.debug("Entering register endpoint");
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        log.debug("Entering login endpoint");
        return ResponseEntity.ok(service.login(request));
    }
}
