package com.example.tracking_order.dto.request;

import lombok.Data;

import java.util.UUID;
@Data
public class ShopDiscount {
    private UUID shopId;
    private UUID discountId;
    private String code;
}
