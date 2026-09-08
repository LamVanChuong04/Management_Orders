package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.InventoryReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.InventoryRes;
import com.example.tracking_order.service.IInventoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
@AllArgsConstructor
@Tag(name = "Inventory Controller")
public class InventoryController {
    private final IInventoryService service;

    @PostMapping()
    public ResponseEntity<BaseResponse<InventoryRes>> create(@RequestBody @Valid InventoryReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(req)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<BaseResponse<InventoryRes>> update(@PathVariable UUID id,
                                             @RequestBody @Valid InventoryReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.update(id, req)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<BaseResponse<?>> delete(@PathVariable UUID id) {
        service.delete(id);
        return new ResponseEntity<>(BaseResponse.ofDeleteSuccess(),  HttpStatus.OK);
    }
}
