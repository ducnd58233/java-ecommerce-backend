package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.controllers.request.AuthenticationRequest;
import com.ecommerce.ecommerce.controllers.request.RegisterRequest;
import com.ecommerce.ecommerce.controllers.response.AuthenticationResponse;
import com.ecommerce.ecommerce.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private static final Logger log = LogManager.getLogger(AuthenticationController.class);

    private final AuthenticationService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse register(
            @Valid @RequestBody RegisterRequest request
    ) {
        log.debug("Entering register endpoint");
        return service.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthenticationResponse login(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        log.debug("Entering login endpoint");
        return service.login(request);
    }
}
