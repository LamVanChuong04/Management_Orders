package com.example.tracking_order.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class ItemProducts {
    private UUID variantId;
    private Integer quantity;
    private BigDecimal price;
    private UUID discountId;
}
