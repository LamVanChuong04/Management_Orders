package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.DiscountReq;
import com.example.tracking_order.dto.response.DiscountRes;

import java.util.List;
import java.util.UUID;

public interface IDiscountService {
    DiscountRes create(DiscountReq req);
    DiscountRes update(UUID id, DiscountReq req);
    List<DiscountRes> findAll();
}
