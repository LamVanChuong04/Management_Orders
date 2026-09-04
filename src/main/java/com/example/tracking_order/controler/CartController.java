package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.CartReq;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.CartRes;
import com.example.tracking_order.service.ICartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carts")
@AllArgsConstructor
public class CartController {
    private final ICartService service;

    @PostMapping()
    public BaseResponse<CartRes> create(@RequestBody @Valid CartReq req) {
        return BaseResponse.ofSuccess(service.create(req));
    }
}
