package com.example.tracking_order.controler;

import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.request.ShopReq;
import com.example.tracking_order.dto.response.ShopRes;
import com.example.tracking_order.service.IShopService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shops")
@AllArgsConstructor
public class ShopController {
    private final IShopService service;

    @PostMapping()
    public ResponseEntity<BaseResponse<ShopRes>> createShop(@RequestBody @Valid ShopReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.createShop(req)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ShopRes>> getShop(@PathVariable UUID id) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getShop(id)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable UUID id) {
        service.deleteShop(id);
        return new ResponseEntity<>(BaseResponse.ofSuccess("DELETED SUCCESSFULL"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ShopRes>> updateShop(@RequestBody @Valid ShopReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.createShop(req)), HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<BaseResponse<List<ShopRes>>> getAllShop() {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getAllShops()),HttpStatus.OK);
    }
}

