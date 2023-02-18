package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.controllers.request.UserRequest;
import com.ecommerce.ecommerce.controllers.response.UserResponse;
import com.ecommerce.ecommerce.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {
    private static final Logger log = LogManager.getLogger(UserController.class);

    private final UserService service;

    @GetMapping("/{id}")
    public UserResponse getProfile(@PathVariable(value = "id") Long id) {
        log.debug("Entering get profile endpoint");
        return service.getProfile(id);
    }

    @PatchMapping
    public String updateProfile(@Valid @RequestBody UserRequest request) {
        log.debug("Entering update profile endpoint");
        return service.updateProfile(request);
    }
}
