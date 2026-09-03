package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.WarehouseRequest;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.InventoryResponse;
import com.example.tracking_order.dto.response.WarehouseResponse;
import com.example.tracking_order.service.IInventoryService;
import com.example.tracking_order.service.IWarehouseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouses")
@AllArgsConstructor
public class WarehouseController {
    private final IWarehouseService service;
    private final IInventoryService inventoryService;

    @PostMapping()
    public BaseResponse<WarehouseResponse> create(@Valid @RequestBody WarehouseRequest req){
        return BaseResponse.ofSuccess(service.create(req));
    }

    @GetMapping("/{id}")
    public BaseResponse<WarehouseResponse> findById(@PathVariable UUID id){
        return BaseResponse.ofSuccess(service.findById(id));
    }
    @PutMapping("/{id}")
    public BaseResponse<WarehouseResponse> update(@PathVariable UUID id,
                                                  @Valid @RequestBody WarehouseRequest req){
        return BaseResponse.ofSuccess(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> delete(@PathVariable UUID id){
        service.delete(id);
        return BaseResponse.ofDeleteSuccess();
    }

    @GetMapping("/search")
    public BaseResponse<List<WarehouseResponse>> findAll(@RequestParam int page,
                                                         @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return BaseResponse.ofSuccess(service.findAll(pageRequest));
    }
    @GetMapping("/{id}/products")
    public BaseResponse<List<InventoryResponse>> findByCategoryId(@PathVariable UUID id){
        return BaseResponse.ofSuccess(inventoryService.getAllProductByWarehouseId(id));
    }
}
