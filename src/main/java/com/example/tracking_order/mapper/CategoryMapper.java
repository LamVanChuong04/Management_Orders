package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.CategoryRequest;
import com.example.tracking_order.dto.response.CategoryResponse;
import com.example.tracking_order.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryEntity toCategoryEntity(CategoryRequest category);
    CategoryResponse toCategoryResponse(CategoryEntity category);
    CategoryEntity updateCategory(CategoryRequest request, CategoryEntity entity);
}
