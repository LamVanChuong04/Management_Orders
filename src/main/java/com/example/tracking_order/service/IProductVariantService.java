package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.ProductVariantReq;
import com.example.tracking_order.dto.response.ProductVariantRes;

import java.util.UUID;

public interface IProductVariantService {
    ProductVariantRes create(ProductVariantReq req);
    ProductVariantRes update(UUID id, ProductVariantReq req);
    void delete(UUID id);
    ProductVariantRes findById(UUID id);
}
