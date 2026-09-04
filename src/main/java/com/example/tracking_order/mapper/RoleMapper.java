package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.RoleReq;
import com.example.tracking_order.dto.response.RoleRes;
import com.example.tracking_order.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleEntity roleReqToEntity(RoleReq roleReq);
    RoleRes entityToResponse(RoleEntity roleEntity);
}
