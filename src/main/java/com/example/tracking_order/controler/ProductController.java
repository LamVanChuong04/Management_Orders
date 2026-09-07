package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.ProductReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.ProductRes;
import com.example.tracking_order.mapper.ProductMapper;
import com.example.tracking_order.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
@Tag(name = "Product Controller")
public class ProductController {
    private IProductService service;

    @Operation(method = "GET", summary = "Get all product", description = "Send a request via this API to get all products")
    @GetMapping()
    public ResponseEntity<BaseResponse<List<ProductRes>>> findAll(@RequestParam int page,
                                                                  @RequestParam int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findAll(pageable)), HttpStatus.OK);
    }
    @Operation(method = "POST", summary = "Add new product", description = "Send a request via this API to add product")
    @PostMapping()
    public ResponseEntity<BaseResponse<ProductRes>> create(@Valid @RequestBody ProductReq productReq)
    {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(productReq)), HttpStatus.CREATED);
    }
    @Operation(method = "PUT", summary = "Update product by id", description = "Send a request via this API to update product by id")
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductRes>> update(@PathVariable UUID id,
                                           @Valid @RequestBody ProductReq productReq){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.update(id, productReq)), HttpStatus.OK);
    }
    @Operation(method = "DELETE", summary = "Delete product by id", description = "Send a request via this API to delete product by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable UUID id){
        service.delete(id);
        return new ResponseEntity<>(BaseResponse.ofDeleteSuccess(), HttpStatus.OK);
    }
    @Operation(method = "GET", summary = "Get detail product by id", description = "Send a request via this API to get product by id")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductRes>> findById(@PathVariable UUID id){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findById(id)), HttpStatus.OK);
    }

}
