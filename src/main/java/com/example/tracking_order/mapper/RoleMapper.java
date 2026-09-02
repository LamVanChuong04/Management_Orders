package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.RoleRequest;
import com.example.tracking_order.dto.response.RoleResponse;
import com.example.tracking_order.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleEntity roleReqToEntity(RoleRequest roleRequest);
    RoleResponse entityToResponse(RoleEntity roleEntity);
}
