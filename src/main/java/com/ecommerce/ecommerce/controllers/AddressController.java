package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.controllers.request.AddressRequest;
import com.ecommerce.ecommerce.models.Address;
import com.ecommerce.ecommerce.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private static final Logger log = LogManager.getLogger(AddressController.class);

    private final AddressService addressService;

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String addAddress(@PathVariable(value = "userId") Long userId, @RequestBody AddressRequest request) {
        log.debug("Entering add address endpoint");
        return addressService.addAddress(userId, request);
    }

    @GetMapping("/{userId}")
    public Address getCurrentAddress(@PathVariable(value = "userId") Long userId) {
        log.debug("Entering get current address endpoint");
        return addressService.getCurrentAddress(userId);
    }

    @GetMapping("/all/{userId}")
    public List<Address> listUserAddress(@PathVariable(value = "userId") Long userId) {
        log.debug("Entering get list user's addresses endpoint");
        return addressService.listUserAddress(userId);
    }
}
