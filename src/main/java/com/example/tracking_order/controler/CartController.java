package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.CartReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.CartDetailRes;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.dto.response.CartRes;
import com.example.tracking_order.entity.UserEntity;
import com.example.tracking_order.service.ICartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
@AllArgsConstructor
@Tag(name = "User Controller")
public class CartController {
    private final ICartService service;
    // lấy chi tiết giỏ hàng theo userId
    @Operation(method = "GET", summary = "Get cart by user id", description = "Send a request via this API to get cart by user id")
    @GetMapping()
    public ResponseEntity<BaseResponse<CartDetailRes>> get(@AuthenticationPrincipal UserEntity user) {
        UUID id = user.getId();
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getById(id)), HttpStatus.OK);
    }
    // them san pham vao gio hang
    @Operation(method = "POST", summary = "Add items", description = "Send a request via this API to add items")
    @PostMapping()
    public ResponseEntity<BaseResponse<CartItemRes>> create(@RequestBody @Valid CartReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.addToCart(req)), HttpStatus.CREATED);
    }
    // update so luong san pham trong cart


}
