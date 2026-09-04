package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.DiscountReq;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.DiscountRes;
import com.example.tracking_order.service.IDiscountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/discounts")
@AllArgsConstructor
public class DiscountController {
    private final IDiscountService service;

    @PostMapping()
    public BaseResponse<DiscountRes> create(@RequestBody @Valid DiscountReq req) {
        return BaseResponse.ofSuccess(service.create(req));
    }

    @PutMapping("/{id}")
    public BaseResponse<DiscountRes> update(@PathVariable UUID id,
                                            @RequestBody @Valid DiscountReq req) {
        return BaseResponse.ofSuccess(service.update(id, req));
    }

    @GetMapping()
    public BaseResponse<List<DiscountRes>> findAll() {
        return BaseResponse.ofSuccess(service.findAll());
    }
}
