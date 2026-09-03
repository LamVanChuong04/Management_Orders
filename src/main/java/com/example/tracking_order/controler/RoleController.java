package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.RoleRequest;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.RoleResponse;
import com.example.tracking_order.mapper.RoleMapper;
import com.example.tracking_order.mapper.UserRoleMapper;
import com.example.tracking_order.service.IRoleService;
import com.example.tracking_order.service.IUserRoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final IRoleService service;

    @PostMapping()
    public BaseResponse<RoleResponse> create(@Valid @RequestBody RoleRequest req) {
        return BaseResponse.ofSuccess(service.createRole(req));
    }

    @GetMapping()
    public BaseResponse<List<RoleResponse>> getAllRoles() {
        return BaseResponse.ofSuccess(service.getAllRoles());
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable UUID id) {
        service.deleteRole(id);
        return BaseResponse.ofDeleteSuccess();
    }
}
