package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.InventoryReq;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.InventoryRes;
import com.example.tracking_order.service.IInventoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
@AllArgsConstructor
public class InventoryController {
    private final IInventoryService service;

    @PostMapping()
    public BaseResponse<InventoryRes> create(@RequestBody @Valid InventoryReq req) {
        return BaseResponse.ofSuccess(service.create(req));
    }

    @PutMapping("/{id}")
    public BaseResponse<InventoryRes> update(@PathVariable UUID id,
                                             @RequestBody @Valid InventoryReq req) {
        return BaseResponse.ofSuccess(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return BaseResponse.ofDeleteSuccess();
    }
}
