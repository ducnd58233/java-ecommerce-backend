package com.ecommerce.ecommerce.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotNull
    @Email(message="invalid email address")
    private String email;

    @NotNull
    @Size(min = 8, max = 16, message = "password length between 8 to 16")
    private String password;
}
