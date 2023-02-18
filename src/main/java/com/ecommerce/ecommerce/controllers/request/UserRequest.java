package com.ecommerce.ecommerce.controllers.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @Nullable
    private String firstname;

    @Nullable
    private String lastname;

    @Nullable
    @Email(message="invalid email address")
    private String email;

    @Nullable
    @Size(min = 8, max = 16, message = "password length between 8 to 16")
    private String password;

    @Nullable
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past
    private Date birthday;

    @Nullable
    @Pattern(regexp="^\\d{11}$", message="invalid phone number")
    private String phone;
}
