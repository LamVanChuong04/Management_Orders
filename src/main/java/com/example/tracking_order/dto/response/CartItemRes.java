package com.example.tracking_order.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CartItemRes {
    private UUID variantId;
    private String productName;
    private String color;
    private String size;
    private BigDecimal unitPrice;
    private int quantity;
    private String statusInventory;
}
