package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.response.MyOrderRes;
import com.example.tracking_order.dto.response.OrderRes;
import com.example.tracking_order.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "priceShippment", target = "feeship")
    @Mapping(source = "discount", target = "discount")
    OrderRes toResponse(OrderEntity entity);
    List<OrderRes> toResponseList(List<OrderEntity> entities);
    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "createdAt", target = "placeOrderDate")
    @Mapping(source = "status", target = "orderStatus")
    @Mapping(source = "total", target = "totalPrice")
    MyOrderRes toRes(OrderEntity entity);

}
