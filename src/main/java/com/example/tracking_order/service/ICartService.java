package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.CartReq;
import com.example.tracking_order.dto.response.CartRes;

public interface ICartService {
    CartRes create(CartReq req);
}
