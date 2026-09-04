package com.example.tracking_order.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderReq {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID addressId;
    @NotEmpty(message = "Total must be not empty")
    private BigDecimal total;
    @NotEmpty(message = "Shipping must be not empty")
    private BigDecimal shipping;
    @NotEmpty(message = "Subtotal must be not empty")
    private BigDecimal subtotal;
    @NotEmpty(message = "Discount must be not empty")
    private BigDecimal discount;
    private String paymentMethod;

}
