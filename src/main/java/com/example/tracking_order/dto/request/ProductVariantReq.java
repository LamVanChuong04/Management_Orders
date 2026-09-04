package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductVariantReq {
    @NotNull(message = "Product id must not be null")
    private UUID productId;
    @Min(value = 0)
    @NotNull(message = "Price must be not null")
    private BigDecimal price;
    @NotEmpty(message = "imageUrl must be not empty")
    private String imageUrl;
    @NotEmpty(message = "color must be not empty")
    private String color;
    @NotNull(message = "size must be not null")
    @Min(value = 36)
    private Integer size;
    @NotNull(message = "weight must be not null")
    @Min(value = 0)
    private Integer weight;
}
