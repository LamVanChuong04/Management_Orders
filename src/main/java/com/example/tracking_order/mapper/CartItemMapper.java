package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.CartItemReq;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(source = "cartId", target = "cart.id")
    @Mapping(source = "productVariantId", target = "productVariant.id")
    CartItemEntity fromCreate(CartItemReq req);
    @Mapping(source = "cart.id", target = "cartId")
    @Mapping(source = "productVariant.id", target = "productVariantId")
    CartItemRes toResponse(CartItemEntity entity);
    CartItemEntity fromUpdate(CartItemReq req, @MappingTarget CartItemEntity entity);

}
