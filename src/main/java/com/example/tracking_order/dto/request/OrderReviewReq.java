package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Data
public class OrderReviewReq {
    @NotNull
    private UUID cartId;
    @NotNull
    private UUID userId;

    private List<ItemProducts> items;
}
