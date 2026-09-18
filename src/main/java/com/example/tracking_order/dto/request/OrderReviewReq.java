package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;
@Data
public class OrderReviewReq {
    @NotNull(message = "id must be not null")
    private UUID cartId;
}
