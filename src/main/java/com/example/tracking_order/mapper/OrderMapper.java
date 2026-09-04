package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.OrderReq;
import com.example.tracking_order.dto.response.OrderRes;
import com.example.tracking_order.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity fromCreate(OrderReq req);
    OrderEntity fromUpdate(OrderReq req, @MappingTarget OrderEntity entity);
    OrderRes toResponse(OrderEntity entity);
    List<OrderRes> toResponseList(List<OrderEntity> entities);
}
