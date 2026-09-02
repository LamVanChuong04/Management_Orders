package com.example.tracking_order.dto.response;

import lombok.Data;

import java.util.UUID;
@Data
public class ProductResponse {
    private UUID id;
    private String productName;
    private String description;
}
