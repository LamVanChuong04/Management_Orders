package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.CartItemReq;
import com.example.tracking_order.dto.response.CartItemRes;

import java.util.UUID;

public interface ICartItemService {
    CartItemRes create(CartItemReq req);
    CartItemRes update(UUID id, CartItemReq req);
}
