package com.example.tracking_order.dto.response;

import com.example.tracking_order.enums.OrderStatus;
import com.example.tracking_order.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrdersRes {
    private LocalDateTime placeOrderDate;
    private PaymentMethod paymentMethod;
    private UUID orderId;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
}
