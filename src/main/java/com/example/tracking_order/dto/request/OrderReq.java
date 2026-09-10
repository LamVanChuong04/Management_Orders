package com.example.tracking_order.dto.request;

import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.enums.PaymentMethod;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class OrderReq {
    @NotNull(message = "User id must be not null")
    private UUID userId;
    private List<CartDetailReq> items;
    private PaymentMethod paymentMethod;
    @NotNull(message = "Price must be not null")
    @Min(0)
    private BigDecimal feeship;


}
