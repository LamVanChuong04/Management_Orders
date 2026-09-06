package com.example.tracking_order.dto.response;

import lombok.Data;

import java.util.UUID;
@Data
public class UserOfDiscountRes {
    private UUID discountId;
    private UUID userId;
}
