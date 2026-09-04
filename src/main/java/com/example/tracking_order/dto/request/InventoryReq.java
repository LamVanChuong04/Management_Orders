package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;
@Data
public class InventoryReq {
    @Min(value = 0, message = "Quantity must be bigger than 0")
    @NotNull(message = "quantity must be not null")
    private Long quantityInStock;
    @NotNull(message = "warehouse id must be not null")
    private UUID warehouseId;
    @NotNull(message = "product variant id must be not null")
    private UUID productVariantId;
}
