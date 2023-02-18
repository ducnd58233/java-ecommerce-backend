package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.controllers.request.AddressRequest;
import com.ecommerce.ecommerce.models.Address;
import com.ecommerce.ecommerce.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("addressService")
@RequiredArgsConstructor
public class AddressService {
    private static final Logger log = LogManager.getLogger(AuthenticationService.class);
    private final AddressRepository addressRepository;

    public String addAddress(Long userId, AddressRequest request) {
        Address address = Address.builder()
                .addr(request.getAddr())
                .userId(userId)
                .title(request.getTitle())
                .lat(request.getLat())
                .lng(request.getLng())
                .build();
        addressRepository.save(address);
        log.info("Add address successful");
        return "Add address successful";
    }

    public Address getCurrentAddress(Long userId) {
        Address address = addressRepository.getCurrentAddress(userId);
        if (address == null) {
            log.error("address not found");
            return null;
        }

        log.info("found user address");
        return address;
    }

    public List<Address> listUserAddress(Long userId) {
        log.info("found list user address");
        return addressRepository.findByUserIdOrderByUpdatedAtDesc(userId);
    }
}
