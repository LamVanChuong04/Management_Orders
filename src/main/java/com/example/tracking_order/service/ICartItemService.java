package com.example.tracking_order.service;


import com.example.tracking_order.dto.request.UpdateQuantityReq;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.entity.CartItemEntity;

import java.util.List;
import java.util.UUID;

public interface ICartItemService {
    List<CartItemRes> toResponseList(List<CartItemEntity> reqs);
    CartItemRes updateQuantity(UpdateQuantityReq req);

}
