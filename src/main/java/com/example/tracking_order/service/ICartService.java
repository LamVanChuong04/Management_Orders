package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.CartReq;
import com.example.tracking_order.dto.response.CartDetailRes;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.dto.response.CartRes;

import java.util.List;
import java.util.UUID;

public interface ICartService {
    CartRes create(CartReq req);
    CartDetailRes getById(UUID id);
    CartItemRes addToCart(CartReq req);
}
