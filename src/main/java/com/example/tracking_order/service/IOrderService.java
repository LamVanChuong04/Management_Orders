package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.OrderReq;
import com.example.tracking_order.dto.request.OrderReviewReq;
import com.example.tracking_order.dto.request.UpdateStatusReq;
import com.example.tracking_order.dto.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public interface IOrderService {
    Page<OrderRes> findAll(Pageable pageable);
    OrderReviewRes sumaryOrder(OrderReviewReq req);
    String checkout(OrderReq req);
    void orderCancel(UUID orderId);
    void updateStatus(UUID orderId, UpdateStatusReq req);
    OrderRes getOrderDetail(UUID orderId);
    Page<MyOrderRes> getOrderOfMe(UUID userId, Pageable pageable);

    int getOrderIsShipping(UUID userId);
    int getOrderIsCompleted(UUID userId);

    OrderDBRes getDB();
    Page<OrderRecentRes> getAllRecentOrder(Pageable pageable);

    Page<OrderConfirmRes>  getAllPendingOrder(Pageable pageable);

    StatisticRes getStatistic(LocalDate createdAt);
}
