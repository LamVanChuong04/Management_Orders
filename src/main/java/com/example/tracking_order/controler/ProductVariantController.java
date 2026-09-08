package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.ProductVariantReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.ProductVariantRes;
import com.example.tracking_order.service.IProductVariantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-variants")
@AllArgsConstructor
@Tag(name = "Product variant Controller")
public class ProductVariantController {
    private final IProductVariantService service;
    @Operation(method = "POST", summary = "Add new product variant", description = "Send a request via this API to add new product variant")
    @PostMapping()
    public ResponseEntity<BaseResponse<ProductVariantRes>> create(@Valid @RequestBody ProductVariantReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(req)),  HttpStatus.CREATED);
    }
    @Operation(method = "POST", summary = "Update product variant", description = "Send a request via this API to update product variant")
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductVariantRes>> update(@PathVariable("id") UUID id,
                                                  @Valid @RequestBody ProductVariantReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.update(id, req)),   HttpStatus.OK);
    }
    @Operation(method = "DELETE", summary = "Delete product variant", description = "Send a request via this API to delete product variant")
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new ResponseEntity<>(BaseResponse.ofDeleteSuccess(), HttpStatus.OK);
    }
    @Operation(method = "GET", summary = "Get product variant", description = "Send a request via this API to get product variant")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductVariantRes>> get(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findById(id)), HttpStatus.OK);
    }
}
