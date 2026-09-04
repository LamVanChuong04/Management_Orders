package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.ProductReq;
import com.example.tracking_order.dto.response.ProductRes;
import com.example.tracking_order.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    ProductEntity create(ProductReq request);
    ProductEntity update(UUID id, ProductReq request);
    void delete(UUID id);
    List<ProductRes> findAll();
    Page<ProductRes> findAll(Pageable pageable);
}
