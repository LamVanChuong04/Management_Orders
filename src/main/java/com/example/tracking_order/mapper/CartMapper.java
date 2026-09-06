package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.CartReq;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.dto.response.CartRes;
import com.example.tracking_order.entity.CartEntity;
import com.example.tracking_order.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {
    @Mapping(source = "userId", target = "user.id")
    CartEntity fromCreate(CartReq req);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "cart.id", target = "cartId")
    CartRes toResponse(CartEntity cart);

    //List<CartItemRes> toResponseList(List<CartItemEntity> cart);
}
