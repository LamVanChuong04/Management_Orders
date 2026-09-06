package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UserOfDiscountReq {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID discountId;
}
