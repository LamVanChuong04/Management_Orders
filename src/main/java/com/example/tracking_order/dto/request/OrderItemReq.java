package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderItemReq {
    @NotNull(message = "id must be not null")
    private UUID orderId;
    @NotNull(message = "id must be not null")
    private UUID productVariantId;
    @NotNull(message = "Value must be not empty")
    @Min(0)
    private Integer quantity;
    @NotNull(message = "Số lượng không được để trống")
    @PositiveOrZero(message = "Số lượng phải lớn hơn hoặc bằng 0")
    private BigDecimal price;
}
