package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.CategoryReq;
import com.example.tracking_order.dto.response.CategoryRes;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    CategoryRes create(CategoryReq category);
    List<CategoryRes> findAll();
    CategoryRes update(UUID id, CategoryReq category);
    void delete(UUID id);
    CategoryRes findById(UUID id);
}
