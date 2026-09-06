package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.WarehouseReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.InventoryRes;
import com.example.tracking_order.dto.response.WarehouseRes;
import com.example.tracking_order.service.IInventoryService;
import com.example.tracking_order.service.IWarehouseService;
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
public class WarehouseController {
    private final IWarehouseService service;
    private final IInventoryService inventoryService;

    @PostMapping()
    public ResponseEntity<BaseResponse<WarehouseRes>> create(@Valid @RequestBody WarehouseReq req){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(req)),  HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<WarehouseRes>> findById(@PathVariable UUID id){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findById(id)),  HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<WarehouseRes>> update(@PathVariable UUID id,
                                             @Valid @RequestBody WarehouseReq req){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.update(id, req)),  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable UUID id){
        service.delete(id);
        return new ResponseEntity<>(BaseResponse.ofDeleteSuccess(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<List<WarehouseRes>>> findAll(@RequestParam int page,
                                                    @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findAll(pageRequest)),  HttpStatus.OK);
    }
    @GetMapping("/{id}/products")
    public ResponseEntity<BaseResponse<List<InventoryRes>>> findByCategoryId(@PathVariable UUID id,
                                                             @RequestParam int page,
                                                             @RequestParam int size){
        PageRequest pageReq = PageRequest.of(page, size);
        return new ResponseEntity<>(BaseResponse.ofSuccess(inventoryService.getAllProductByWarehouseId(id, pageReq)),  HttpStatus.OK);
    }
}
