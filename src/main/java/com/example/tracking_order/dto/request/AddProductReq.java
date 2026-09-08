package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;
@Data
public class AddProductReq {
    @NotNull(message = "Product variant id must be not null")
    private UUID productVariantId;
    @NotEmpty(message = "Product name must be not empty")
    @Size(min = 3, max = 50, message = "Product name must certain the least 6 characters")
    private String productName;
    @NotNull(message = "Số lượng không được để trống")
    @PositiveOrZero(message = "Số lượng phải lớn hơn hoặc bằng 0")
    private Integer quantity;

}
