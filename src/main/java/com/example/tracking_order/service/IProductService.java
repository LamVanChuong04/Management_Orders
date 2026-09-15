package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.ProductReq;
import com.example.tracking_order.dto.response.ProductDetailRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    ProductDetailRes create(ProductReq request);
    ProductDetailRes update(UUID id, ProductReq request);
    void delete(UUID id);
    List<ProductDetailRes> findAll();
    Page<ProductDetailRes> findAll(Pageable pageable);
    ProductDetailRes findById(UUID id);
    List<ProductDetailRes> findByCategoryId(UUID id);
}
