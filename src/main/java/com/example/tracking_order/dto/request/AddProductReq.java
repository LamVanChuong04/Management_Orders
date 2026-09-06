package com.example.tracking_order.dto.request;

import lombok.Data;

import java.util.UUID;
@Data
public class AddProductReq {
    private UUID productVariantId;
    private String productName;
    private Integer quantity;

}
