package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.ProductReq;
import com.example.tracking_order.dto.response.ProductDetailRes;
import com.example.tracking_order.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "shopId", target = "shop.id")
    ProductEntity toProductEntity(ProductReq productReq);
    @Mapping(source = "shop.id", target = "shopId")
    ProductDetailRes toProductResponse(ProductEntity entity);
    ProductEntity updateProduct(ProductReq request, @MappingTarget ProductEntity entity);
    List<ProductDetailRes> toProductResponseList(List<ProductEntity> entities);
}
