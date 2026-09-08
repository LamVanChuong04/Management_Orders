package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateProductQuantity {
    @NotNull(message = "Số lượng không được để trống")
    @PositiveOrZero(message = "Số lượng phải lớn hơn hoặc bằng 0")
    private Integer oldQuantity;
    @NotNull(message = "Số lượng không được để trống")
    @PositiveOrZero(message = "Số lượng phải lớn hơn hoặc bằng 0")
    private Integer newQuantity;
    @NotNull(message = "id must be not null")
    private UUID productVariantId;
    @NotNull(message = "id must be not null")
    private UUID userId;

 }
