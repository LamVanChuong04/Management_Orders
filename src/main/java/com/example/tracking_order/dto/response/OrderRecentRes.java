package com.example.tracking_order.dto.response;

import com.example.tracking_order.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class OrderRecentRes {
    private UUID orderId;
    private String userName;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime createdAt;
    private BigDecimal amount;
    private OrderStatus orderStatus;
    private String carrier;
}
