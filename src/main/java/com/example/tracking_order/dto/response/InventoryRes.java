package com.example.tracking_order.dto.response;

import lombok.Data;

import java.util.UUID;
@Data
public class InventoryRes {
    private Long quantityInStock;
    //private UUID warehouseId;
    private UUID productVariantId;
}
