package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.CategoryRequest;
import com.example.tracking_order.dto.response.CategoryResponse;
import com.example.tracking_order.entity.CategoryEntity;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    CategoryResponse create(CategoryRequest category);
    List<CategoryResponse> findAll();
    CategoryResponse update(UUID id, CategoryRequest category);
    void delete(UUID id);
    CategoryResponse findById(UUID id);
}
