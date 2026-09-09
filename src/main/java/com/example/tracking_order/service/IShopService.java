package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.ShopReq;
import com.example.tracking_order.dto.response.ShopRes;

import java.util.List;
import java.util.UUID;

public interface IShopService {
    ShopRes createShop(ShopReq req);
    ShopRes updateShop(UUID id, ShopReq req);
    void deleteShop(UUID id);
    ShopRes getShop(UUID id);
    List<ShopRes> getAllShops();
}
