package com.example.tracking_order.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CartDetailRes {
//    private UUID cartId;
    private Integer totalQuantity;
    private List<CartItemRes> items;
}
