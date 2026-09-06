package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.CartItemReq;
import com.example.tracking_order.dto.request.UpdateQuantityRequest;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.service.ICartItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart-items")
@AllArgsConstructor
public class CartItemController {
    private final ICartItemService service;

    @PostMapping()
    public ResponseEntity<BaseResponse<CartItemRes>> create(@Valid @RequestBody CartItemReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(req)), HttpStatus.CREATED);
    }
    @PatchMapping("/{cartItemId}")
    public ResponseEntity<BaseResponse<CartItemRes>> updateQuantity(@PathVariable UUID cartItemId,
                                                    @Valid @RequestBody UpdateQuantityRequest req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.updateQuantity(cartItemId,req)), HttpStatus.OK);

    }

}
