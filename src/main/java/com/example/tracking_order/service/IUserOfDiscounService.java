package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.UserOfDiscountReq;
import com.example.tracking_order.dto.response.UserOfDiscountRes;

import java.util.UUID;

public interface IUserOfDiscounService {
    UserOfDiscountRes create(UserOfDiscountReq req);
    UserOfDiscountRes update(UUID id, UserOfDiscountReq req);
}
