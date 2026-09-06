package com.example.tracking_order.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateProductQuantity {
    private Integer oldQuantity;
    private Integer newQuantity;
    private UUID productVariantId;
    private UUID userId;

 }
