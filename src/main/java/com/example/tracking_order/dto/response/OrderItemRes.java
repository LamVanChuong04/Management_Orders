package com.example.tracking_order.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class OrderItemRes {
    private UUID orderId;
    private ProductVariantRes productVariant;
    private BigDecimal quantity;
    private BigDecimal price;
}
