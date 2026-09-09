package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.DiscountReq;
import com.example.tracking_order.dto.response.DiscountRes;
import com.example.tracking_order.entity.DiscountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    @Mapping(source = "shopId", target = "shop.id")
    @Mapping(source = "quantity", target = "quantity")
    DiscountEntity fromCreate(DiscountReq req);
    DiscountEntity fromUpdate(DiscountReq req, @MappingTarget DiscountEntity entity);
    @Mapping(source = "endDate", target = "HSD")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "shop.id", target = "shopId")
    DiscountRes toResponse(DiscountEntity entity);
}
