package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.RoleReq;
import com.example.tracking_order.dto.response.RoleRes;

import java.util.List;
import java.util.UUID;

public interface IRoleService {
    RoleRes createRole(RoleReq req);
    List<RoleRes> getAllRoles();
    void deleteRole(UUID id);
}
