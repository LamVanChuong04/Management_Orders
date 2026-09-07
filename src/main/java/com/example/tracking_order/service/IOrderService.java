package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.OrderReq;
import com.example.tracking_order.dto.request.OrderReviewReq;
import com.example.tracking_order.dto.response.OrderRes;
import com.example.tracking_order.dto.response.OrderReviewRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IOrderService {
    String create(OrderReq req);
    Page<OrderRes> findAll(Pageable pageable);
    OrderReviewRes review(OrderReviewReq req);
}
