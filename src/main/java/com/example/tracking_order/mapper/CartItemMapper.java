package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.CartItemReq;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(source = "cartId", target = "cart.id")
    @Mapping(source = "productVariantId", target = "productVariant.id")
    CartItemEntity fromCreate(CartItemReq req);


    @Mapping(source = "productVariant.product.id", target = "productId")
    @Mapping(source = "productVariant.product.productName", target = "productName")
    @Mapping(source = "productVariant.id", target = "variantId")
    @Mapping(source = "productVariant.color", target = "color")
    @Mapping(source = "productVariant.size", target = "size")
    @Mapping(source = "productVariant.price", target = "price")
    @Mapping(source = "quantity", target = "quantity")
    CartItemRes toResponse(CartItemEntity entity);

    CartItemEntity fromUpdate(CartItemReq req, @MappingTarget CartItemEntity entity);

    List<CartItemRes> toResponseList(List<CartItemEntity> entities);
}
