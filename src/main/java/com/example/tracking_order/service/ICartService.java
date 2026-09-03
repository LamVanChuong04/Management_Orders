package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.CartRequest;
import com.example.tracking_order.dto.response.CartResponse;

public interface ICartService {
    CartResponse create(CartRequest req);
}
