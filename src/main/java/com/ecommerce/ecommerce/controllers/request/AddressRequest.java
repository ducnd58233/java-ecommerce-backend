package com.ecommerce.ecommerce.controllers.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    @NotNull(message="address cannot be empty")
    private String addr;
    @NotNull
    private Long userId;
    @Nullable
    private String title;
    @Nullable
    private Double lat;
    @Nullable
    private Double lng;
}
