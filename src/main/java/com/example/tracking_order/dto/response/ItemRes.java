package com.example.tracking_order.dto.response;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ItemRes {
    private String productName;
    private BigDecimal unitPrice;
    private int quantity;
    private String statusInventory;
    private int quantityInStock;
}
