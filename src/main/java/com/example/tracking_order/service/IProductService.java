package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.ProductRequest;
import com.example.tracking_order.dto.response.ProductResponse;
import com.example.tracking_order.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    ProductEntity create(ProductRequest request);
    ProductEntity update(UUID id, ProductRequest request);
    void delete(UUID id);
    List<ProductResponse> findAll();
    Page<ProductResponse> findAll(Pageable pageable);
}
