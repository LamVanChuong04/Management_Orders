package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.CategoryReq;
import com.example.tracking_order.dto.response.CategoryRes;
import com.example.tracking_order.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ProductMapper.class})
public interface CategoryMapper {
    CategoryEntity toCategoryEntity(CategoryReq category);
    CategoryRes toCategoryResponse(CategoryEntity category);
    CategoryEntity updateCategory(CategoryReq request, @MappingTarget CategoryEntity entity);
    List<CategoryRes> toCategoryResponseList(List<CategoryEntity> entities);
}
