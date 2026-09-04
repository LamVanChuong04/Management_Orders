package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.OrderReq;
import com.example.tracking_order.dto.response.OrderRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IOrderService {
    OrderRes create(OrderReq req);
    OrderRes update(UUID id, OrderReq req);
    Page<OrderRes> findAll(Pageable pageable);
}
