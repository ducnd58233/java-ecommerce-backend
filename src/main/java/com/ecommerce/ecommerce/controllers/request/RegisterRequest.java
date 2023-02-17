package com.ecommerce.ecommerce.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull(message="first name should not be empty")
    @NotBlank(message="first name should not be empty")
    private String firstname;

    @NotNull(message="last name should not be empty")
    @NotBlank(message="last name should not be empty")
    private String lastname;

    @Email(message="invalid email address")
    private String email;

    @NotNull
    @Size(min = 8, max = 16, message = "password length between 8 to 16")
    private String password;

    @NotNull(message="date of birth should not be empty")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past
    private Date birthday;

    @NotNull @NotBlank
    @Pattern(regexp="^\\d{11}$", message="invalid phone number")
    private String phone;

    @NotNull(message="address should not be empty")
    @NotBlank(message="address should not be empty")
    private String address;
}
