package com.example.tracking_order.dto.response;

import lombok.Data;

import java.util.UUID;
@Data
public class InventoryRes {
    private Long quantityInStock;
    private String addressWarehouse;

    public InventoryRes(Long quantityInStock, String addressWarehouse, UUID productVariantId, String status) {
        this.quantityInStock = quantityInStock;
        this.addressWarehouse = addressWarehouse;
        this.productVariantId = productVariantId;
        this.status = status;
    }

    private UUID productVariantId;
    private String status;
}
