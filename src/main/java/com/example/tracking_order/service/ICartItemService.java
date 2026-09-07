package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.CartItemReq;
import com.example.tracking_order.dto.request.UpdateQuantityRequest;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.entity.CartItemEntity;

import java.util.List;
import java.util.UUID;

public interface ICartItemService {
    CartItemRes create(CartItemReq req);
    CartItemRes update(UUID id, CartItemReq req);
    List<CartItemRes> toResponseList(List<CartItemEntity> reqs);
    CartItemRes updateQuantity(UUID userId, UpdateQuantityRequest reqy);
    //void delete(UUID itemId);

    //void deleteById(UUID id);
}
