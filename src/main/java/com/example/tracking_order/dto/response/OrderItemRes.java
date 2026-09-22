package com.example.tracking_order.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRes {
    private String productName;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private String color;
    private String size;

}
