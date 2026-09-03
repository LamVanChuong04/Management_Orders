package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.CartRequest;
import com.example.tracking_order.dto.response.CartResponse;
import com.example.tracking_order.entity.CartEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CartMapper {
    CartEntity fromCreate(CartRequest req);
    CartResponse toResponse(CartEntity cart);
}
