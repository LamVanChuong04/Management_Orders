package com.example.tracking_order.dto.response;


import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductVariantRes {
    private UUID productId;
    private BigDecimal price;
    private String imageUrl;
    private String color;
    private String size;
    private String weight;
}
