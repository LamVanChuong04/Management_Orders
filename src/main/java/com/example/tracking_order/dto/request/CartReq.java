package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public class CartReq {
    @NotNull(message = "User id must be not null")
    private UUID userId;
    @NotNull(message = "Product variant id must be not null")
    private UUID productVariantId;
    @NotNull(message = "Số lượng không được để trống")
    @PositiveOrZero(message = "Số lượng phải lớn hơn hoặc bằng 0")
    private Integer quantity;
}
