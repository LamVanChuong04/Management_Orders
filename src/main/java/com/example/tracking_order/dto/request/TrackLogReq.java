package com.example.tracking_order.dto.request;

import com.example.tracking_order.enums.OrderStatus;
import com.example.tracking_order.service.impl.OrderServiceImp;
import lombok.Data;

import java.util.UUID;

@Data
public class TrackLogReq {
    private UUID orderId;
    private OrderStatus oldStatus;
    private OrderStatus newStatus;
}
