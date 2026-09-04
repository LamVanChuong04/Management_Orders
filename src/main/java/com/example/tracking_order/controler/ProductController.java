package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.ProductReq;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.ProductRes;
import com.example.tracking_order.mapper.ProductMapper;
import com.example.tracking_order.service.IProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
//@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private IProductService service;
    private ProductMapper mapper;

    @GetMapping("/products")
    public BaseResponse<List<ProductRes>> findAll(@RequestParam int page,
                                                  @RequestParam int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return BaseResponse.ofSuccess(service.findAll(pageable));
    }

    @PostMapping("/products")
    public BaseResponse<ProductRes> create(@Valid @RequestBody ProductReq productReq)
    {
        return BaseResponse.ofSuccess(mapper.toProductResponse(service.create(productReq)));
    }

    @PutMapping("/products/{id}")
    public BaseResponse<ProductRes> update(@PathVariable UUID id,
                                           @Valid @RequestBody ProductReq productReq){
        return BaseResponse.ofSuccess(mapper.toProductResponse(service.update(id, productReq)));
    }

    @DeleteMapping("/products/{id}")
    public BaseResponse<?> delete(@PathVariable UUID id){
        service.delete(id);
        return BaseResponse.ofDeleteSuccess();
    }

}
