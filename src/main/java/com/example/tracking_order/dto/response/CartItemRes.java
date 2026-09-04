package com.example.tracking_order.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CartItemRes {
    private Integer quantity;
    private UUID cartId;
    private UUID productVariantId;
    private Boolean isSelected;
}
