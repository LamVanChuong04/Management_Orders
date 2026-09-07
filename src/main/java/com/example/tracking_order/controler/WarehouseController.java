package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.WarehouseReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.InventoryRes;
import com.example.tracking_order.dto.response.WarehouseRes;
import com.example.tracking_order.service.IInventoryService;
import com.example.tracking_order.service.IWarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouses")
@AllArgsConstructor
@Tag(name = "Warehouse Controller")
public class WarehouseController {
    private final IWarehouseService service;
    private final IInventoryService inventoryService;
    @Operation(method = "POST", summary = "Add new warehouse", description = "Send a request via this API to create new warehouse")
    @PostMapping()
    public ResponseEntity<BaseResponse<WarehouseRes>> create(@Valid @RequestBody WarehouseReq req){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(req)),  HttpStatus.CREATED);
    }
    @Operation(method = "GET", summary = "Get detail warehouse by id", description = "Send a request via this API to get detail warehouse by id")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<WarehouseRes>> findById(@PathVariable UUID id){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findById(id)),  HttpStatus.OK);
    }
    @Operation(method = "PUT", summary = "Update warehouse by id", description = "Send a request via this API to update warehouse by id")
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<WarehouseRes>> update(@PathVariable UUID id,
                                             @Valid @RequestBody WarehouseReq req){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.update(id, req)),  HttpStatus.OK);
    }
    @Operation(method = "DELETE", summary = "Delete warehouse by id", description = "Send a request via this API to delete warehouse by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable UUID id){
        service.delete(id);
        return new ResponseEntity<>(BaseResponse.ofDeleteSuccess(), HttpStatus.OK);
    }
    @Operation(method = "GET", summary = "Get all warehouse and pageable", description = "Send a request via this API to get all warehouse")
    @GetMapping("/search")
    public ResponseEntity<BaseResponse<List<WarehouseRes>>> findAll(@RequestParam int page,
                                                    @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findAll(pageRequest)),  HttpStatus.OK);
    }
    @Operation(method = "GET", summary = "Get all product by warehouse id and pageable", description = "Send a request via this API to get all product by warehouse")
    @GetMapping("/{id}/products")
    public ResponseEntity<BaseResponse<List<InventoryRes>>> findByCategoryId(@PathVariable UUID id,
                                                             @RequestParam int page,
                                                             @RequestParam int size){
        PageRequest pageReq = PageRequest.of(page, size);
        return new ResponseEntity<>(BaseResponse.ofSuccess(inventoryService.getAllProductByWarehouseId(id, pageReq)),  HttpStatus.OK);
    }
}
