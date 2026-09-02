package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.ProductRequest;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.ProductResponse;
import com.example.tracking_order.entity.ProductEntity;
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
    public BaseResponse<List<ProductResponse>> findAll(@RequestParam int page,
                                                       @RequestParam int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return BaseResponse.ofSuccess(service.findAll(pageable));
    }

    @PostMapping("/products")
    public BaseResponse<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest)
    {
        return BaseResponse.ofSuccess(mapper.toProductResponse(service.create(productRequest)));
    }

    @PutMapping("/products/{id}")
    public BaseResponse<ProductResponse> update(@PathVariable UUID id,
                                                @Valid @RequestBody ProductRequest productRequest){
        return BaseResponse.ofSuccess(mapper.toProductResponse(service.update(id, productRequest)));
    }

    @DeleteMapping("/products/{id}")
    public BaseResponse<?> delete(@PathVariable UUID id){
        service.delete(id);
        return BaseResponse.ofDeleteSuccess();
    }

}
