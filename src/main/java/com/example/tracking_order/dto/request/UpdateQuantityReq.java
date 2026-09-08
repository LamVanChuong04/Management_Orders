package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateQuantityReq {
    @NotNull(message = "Số lượng không được để trống")
    @PositiveOrZero(message = "Số lượng phải lớn hơn hoặc bằng 0")
    private Integer quantity;
    @NotNull(message = "Cart item id must be not null")
    private UUID productVarianId;
    @NotNull(message = "Cart item id must be not null")
    private UUID cartId;
}
