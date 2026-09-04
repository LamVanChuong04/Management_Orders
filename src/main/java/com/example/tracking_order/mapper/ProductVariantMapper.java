package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.ProductVariantReq;
import com.example.tracking_order.dto.response.ProductVariantRes;
import com.example.tracking_order.entity.ProductVariantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    @Mapping(source = "productId", target = "product.id")
    ProductVariantEntity fromCreate(ProductVariantReq req);
    ProductVariantEntity fromUpdate(ProductVariantReq req, @MappingTarget ProductVariantEntity entity);
    @Mapping(source = "product.id", target = "productId")
    ProductVariantRes fromEntity(ProductVariantEntity entity);
}
