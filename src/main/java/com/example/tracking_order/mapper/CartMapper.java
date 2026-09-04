package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.CartReq;
import com.example.tracking_order.dto.response.CartRes;
import com.example.tracking_order.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(source = "userId", target = "user.id")
    CartEntity fromCreate(CartReq req);
    @Mapping(source = "user.id", target = "userId")
    CartRes toResponse(CartEntity cart);
}
