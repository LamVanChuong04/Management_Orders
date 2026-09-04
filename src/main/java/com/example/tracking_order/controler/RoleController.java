package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.RoleReq;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.RoleRes;
import com.example.tracking_order.service.IRoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final IRoleService service;

    @PostMapping()
    public BaseResponse<RoleRes> create(@Valid @RequestBody RoleReq req) {
        return BaseResponse.ofSuccess(service.createRole(req));
    }

    @GetMapping()
    public BaseResponse<List<RoleRes>> getAllRoles() {
        return BaseResponse.ofSuccess(service.getAllRoles());
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable UUID id) {
        service.deleteRole(id);
        return BaseResponse.ofDeleteSuccess();
    }
}
