package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.ProductRequest;
import com.example.tracking_order.dto.response.ProductResponse;
import com.example.tracking_order.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity toProductEntity(ProductRequest productRequest);
    ProductResponse toProductResponse(ProductEntity entity);
    ProductEntity updateProduct(ProductRequest request, @MappingTarget ProductEntity entity);
    List<ProductResponse> toProductResponseList(List<ProductEntity> entities);
}
