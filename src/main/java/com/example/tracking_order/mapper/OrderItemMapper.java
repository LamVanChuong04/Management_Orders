package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.response.OrderItemRes;
import com.example.tracking_order.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "productVariant.id", target = "productVariantId")
    @Mapping(source = "price", target = "unitPrice")
    OrderItemRes toResponse(OrderItemEntity entity);
}
