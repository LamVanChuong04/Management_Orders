package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.RoleRequest;
import com.example.tracking_order.dto.response.RoleResponse;
import com.example.tracking_order.entity.RoleEntity;

import java.util.List;
import java.util.UUID;

public interface IRoleService {
    RoleEntity createRole(RoleRequest role);
    List<RoleResponse> getAllRoles();
    String deleteRole(UUID id);
}
