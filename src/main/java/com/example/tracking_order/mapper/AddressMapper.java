package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.AddressReq;
import com.example.tracking_order.dto.response.AddressRes;
import com.example.tracking_order.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "isDefault", target = "isDefault")
    AddressEntity fromCreate(AddressReq req);
    AddressEntity fromUpdate(AddressReq req, @MappingTarget AddressEntity entity);
    //@Mapping(source = "user.id", target = "userId")
    AddressRes toResponse(AddressEntity entity);
}
