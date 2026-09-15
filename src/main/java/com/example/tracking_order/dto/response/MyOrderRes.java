package com.example.tracking_order.dto.response;

import com.example.tracking_order.enums.OrderStatus;
import com.example.tracking_order.enums.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class MyOrderRes {
    public MyOrderRes(LocalDateTime placeOrderDate, PaymentMethod paymentMethod, UUID orderId, OrderStatus orderStatus, BigDecimal totalPrice) {
        this.placeOrderDate = placeOrderDate;
        this.paymentMethod = paymentMethod;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
    }
    public MyOrderRes(){}
    private LocalDateTime placeOrderDate;
    private PaymentMethod paymentMethod;
    private UUID orderId;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
}
