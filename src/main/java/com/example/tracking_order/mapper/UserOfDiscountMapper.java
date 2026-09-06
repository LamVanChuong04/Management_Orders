package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.UserOfDiscountReq;
import com.example.tracking_order.dto.response.UserOfDiscountRes;
import com.example.tracking_order.entity.UserOfDiscounteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserOfDiscountMapper {
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "discountId", target = "discount.id")
    UserOfDiscounteEntity fromCreate(UserOfDiscountReq req);
    UserOfDiscounteEntity fromUpdate(UserOfDiscountReq req, @MappingTarget UserOfDiscounteEntity entity);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "discount.id", target = "discountId")
    UserOfDiscountRes toResponse(UserOfDiscounteEntity entity);
}
