package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.CartReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.CartDetailRes;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.dto.response.CartRes;
import com.example.tracking_order.service.ICartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
@AllArgsConstructor
public class CartController {
    private final ICartService service;
    // lấy chi tiết giỏ hàng theo userId
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CartDetailRes>> get(@PathVariable UUID id) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getById(id)), HttpStatus.OK);
    }
    // them san pham vao gio hang
    @PostMapping()
    public ResponseEntity<BaseResponse<CartItemRes>> create(@RequestBody @Valid CartReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.addToCart(req)), HttpStatus.CREATED);
    }
}
