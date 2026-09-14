package com.example.tracking_order.dto.response;

import com.example.tracking_order.enums.OrderStatus;
import com.example.tracking_order.enums.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class OrderDashBoardRes {
    private int isShipping;
    private int isCompleted;
    private LocalDateTime placeOrderDate;
    private PaymentMethod paymentMethod;
    private UUID orderId;
    private int quantityItem;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
}
