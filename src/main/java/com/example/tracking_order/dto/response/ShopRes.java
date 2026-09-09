package com.example.tracking_order.dto.response;

import lombok.Data;

import java.util.UUID;
@Data
public class ShopRes {
    private String shopName;
    private String email;
    private UUID id;
}
