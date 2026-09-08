package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Data
public class OrderReviewReq {
    @NotNull(message = "id must be not null")
    private UUID cartId;
    @NotNull(message = "id must be not null")
    private UUID userId;

    private List<ItemProducts> items;
    @NotNull(message = "Value must be not null")
    @Min(0)
    private BigDecimal price;
    @NotNull(message = "Value must be not null")
    @Min(0)
    private BigDecimal feeship;
}
