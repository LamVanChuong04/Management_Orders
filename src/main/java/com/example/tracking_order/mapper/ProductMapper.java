package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.ProductReq;
import com.example.tracking_order.dto.response.ProductRes;
import com.example.tracking_order.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity toProductEntity(ProductReq productReq);
    ProductRes toProductResponse(ProductEntity entity);
    ProductEntity updateProduct(ProductReq request, @MappingTarget ProductEntity entity);
    List<ProductRes> toProductResponseList(List<ProductEntity> entities);
}
