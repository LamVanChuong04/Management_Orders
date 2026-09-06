package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.ProductReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.ProductRes;
import com.example.tracking_order.mapper.ProductMapper;
import com.example.tracking_order.service.IProductService;
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
public class ProductController {
    private IProductService service;
    private ProductMapper mapper;

    @GetMapping()
    public ResponseEntity<BaseResponse<List<ProductRes>>> findAll(@RequestParam int page,
                                                                  @RequestParam int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findAll(pageable)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<ProductRes>> create(@Valid @RequestBody ProductReq productReq)
    {
        return new ResponseEntity<>(BaseResponse.ofSuccess(mapper.toProductResponse(service.create(productReq))), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductRes>> update(@PathVariable UUID id,
                                           @Valid @RequestBody ProductReq productReq){
        return new ResponseEntity<>(BaseResponse.ofSuccess(mapper.toProductResponse(service.update(id, productReq))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable UUID id){
        service.delete(id);
        return new ResponseEntity<>(BaseResponse.ofDeleteSuccess(), HttpStatus.OK);
    }

}
