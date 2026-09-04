package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.DiscountReq;
import com.example.tracking_order.dto.response.DiscountRes;
import com.example.tracking_order.entity.DiscountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    DiscountEntity fromCreate(DiscountReq req);
    DiscountEntity fromUpdate(DiscountReq req, @MappingTarget DiscountEntity entity);
    @Mapping(source = "endDate", target = "HSD")
    DiscountRes toResponse(DiscountEntity entity);
}
