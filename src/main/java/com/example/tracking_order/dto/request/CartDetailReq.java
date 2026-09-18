package com.example.tracking_order.dto.request;

import com.example.tracking_order.dto.response.CartItemRes;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class CartDetailReq {
    private UUID variantId;
    private int quantity;
}
