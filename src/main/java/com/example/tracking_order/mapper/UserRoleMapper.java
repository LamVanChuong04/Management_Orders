package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.UserRoleRequest;
import com.example.tracking_order.entity.UserRoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleEntity toUserRoleRequest(UserRoleRequest userRoleRequest);
}
