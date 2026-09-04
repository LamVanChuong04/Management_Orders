package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.ProductVariantReq;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.ProductVariantRes;
import com.example.tracking_order.service.IProductVariantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-variants")
@AllArgsConstructor
public class ProductVariantController {
    private final IProductVariantService service;

    @PostMapping()
    public BaseResponse<ProductVariantRes> create(@Valid @RequestBody ProductVariantReq req) {
        return BaseResponse.ofSuccess(service.create(req));
    }

    @PutMapping("/{id}")
    public BaseResponse<ProductVariantRes> update(@PathVariable("id") UUID id,
                                                  @Valid @RequestBody ProductVariantReq req) {
        return BaseResponse.ofSuccess(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return BaseResponse.ofDeleteSuccess();
    }

    @GetMapping("/{id}")
    public BaseResponse<ProductVariantRes> get(@PathVariable("id") UUID id) {
        return BaseResponse.ofSuccess(service.findById(id));
    }
}
