package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.common.CustomStatus;
import com.ecommerce.ecommerce.controllers.request.UserRequest;
import com.ecommerce.ecommerce.controllers.response.UserResponse;
import com.ecommerce.ecommerce.models.User;
import com.ecommerce.ecommerce.repositories.UserRepository;
import com.ecommerce.ecommerce.securities.exception.ApiRequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("userService")
@Transactional
@RequiredArgsConstructor
public class UserService {
    private static final Logger log = LogManager.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;

    public UserResponse getProfile(Long id) {
        User user = userRepository.findByIdAndStatus(id, CustomStatus.ACTIVE.getValue()).orElse(null);

        if (user == null) {
            log.error("User with id = " + id + " not found");
            throw new ApiRequestException("user not found", HttpStatus.NOT_FOUND);
        }

        UserResponse response = UserResponse.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .birthday(user.getBirthday())
                .build();
        log.info("Get user information successful");
        return response;
    };

    public String updateProfile(UserRequest request) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(currentUser).orElse(null);

        user.setFirstname(request.getFirstname() == null ? user.getFirstname() : request.getFirstname());
        user.setLastname(request.getLastname() == null ? user.getLastname() : request.getLastname());
        user.setPhone(request.getPhone() == null ? user.getPhone() : request.getPhone());
        user.setBirthday(request.getBirthday() == null ? user.getBirthday() : request.getBirthday());

        userRepository.save(user);
        log.info("Updated user " + user.getId() + " information successful");
        return "user info updated successful";
    }
}
