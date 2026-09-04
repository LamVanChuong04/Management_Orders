package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class AddressReq {
    @NotNull(message = "User id must be not null")
    private UUID userId;

    @NotEmpty(message = "Province must be not empty")
    @Size(min = 6, max = 30, message = "Province must certain the least 6 characters")
    private String province;

    @NotEmpty(message = "District must be not empty")
    @Size(min = 6, max = 30, message = "Province must certain the least 6 characters")
    private String district;

    @NotEmpty(message = "Ward must be not empty")
    @Size(min = 6, max = 30, message = "Province must certain the least 6 characters")
    private String ward;

    @NotEmpty(message = "Street must be not empty")
    @Size(min = 6, max = 30, message = "Province must certain the least 6 characters")
    private String street;

    private Boolean isDefault = false;
}
