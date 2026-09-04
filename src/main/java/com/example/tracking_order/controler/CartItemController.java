package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.CartItemReq;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.service.ICartItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart-items")
@AllArgsConstructor
public class CartItemController {
    private final ICartItemService service;

    @PostMapping()
    public BaseResponse<CartItemRes> create(@Valid @RequestBody CartItemReq req) {
        return BaseResponse.ofSuccess(service.create(req));
    }

}
