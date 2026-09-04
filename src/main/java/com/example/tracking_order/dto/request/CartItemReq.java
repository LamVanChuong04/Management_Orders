package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CartItemReq {
    @NotNull(message = "quantity must be not null")
    private Integer quantity;
    @NotNull(message = "cart id must be not null")
    private UUID cartId;
    @NotNull(message = "product variant id must be not null")
    private UUID productVariantId;
    private Boolean isSelected;
}
