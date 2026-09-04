package com.example.tracking_order.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderItemReq {
    private UUID orderId;
    private UUID productVariantId;
    private Integer quantity;
    private BigDecimal price;
}
