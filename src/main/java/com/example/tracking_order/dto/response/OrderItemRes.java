package com.example.tracking_order.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class OrderItemRes {
    private UUID productVariantId;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private String color;
    private String size;
    //private Integer weight;
}
