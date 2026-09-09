package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.ShopReq;
import com.example.tracking_order.dto.response.ShopRes;
import com.example.tracking_order.entity.ShopEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    ShopEntity fromCreate(ShopReq req);
    ShopRes toResponse(ShopEntity shop);
    ShopEntity fromUpdate(ShopReq req, @MappingTarget ShopEntity shop);

}
