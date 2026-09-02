package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.CategoryRequest;
import com.example.tracking_order.entity.CategoryEntity;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    CategoryEntity create(CategoryRequest category);
    List<CategoryEntity> findAll();
    CategoryEntity update(UUID id, CategoryRequest category);
    void delete(UUID id);
    CategoryEntity findById(UUID id);
}
