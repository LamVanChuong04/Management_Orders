package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class ItemProducts {
    @NotNull(message = "Product variant id must be not null")
    private UUID variantId;
    @NotNull(message = "Số lượng không được để trống")
    @PositiveOrZero(message = "Số lượng phải lớn hơn hoặc bằng 0")
    private Integer quantity;
    @NotNull(message = "Value must be not empty")
    @Min(0)
    private BigDecimal price;
    @NotNull(message = "Discount id must be not null")
    private UUID discountId;
}
