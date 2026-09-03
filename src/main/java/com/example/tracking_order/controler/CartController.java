package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.CartRequest;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.CartResponse;
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
    public BaseResponse<CartResponse> create(@RequestBody @Valid CartRequest req) {
        return BaseResponse.ofSuccess(service.create(req));
    }
}
