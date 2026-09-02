package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.CategoryRequest;
import com.example.tracking_order.dto.response.CategoryResponse;
import com.example.tracking_order.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryEntity toCategoryEntity(CategoryRequest category);
    CategoryResponse toCategoryResponse(CategoryEntity category);
    CategoryEntity updateCategory(CategoryRequest request, @MappingTarget CategoryEntity entity);

    List<CategoryResponse> toCategoryResponseList(List<CategoryEntity> entities);
}
