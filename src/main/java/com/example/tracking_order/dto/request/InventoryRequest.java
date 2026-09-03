package com.example.tracking_order.dto.request;

import lombok.Data;

import java.util.UUID;
@Data
public class InventoryRequest {
    private Long quantityInStock;
    private UUID warehouse_id;
}
