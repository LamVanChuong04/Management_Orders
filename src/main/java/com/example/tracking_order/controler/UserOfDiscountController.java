package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.UserOfDiscountReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.UserOfDiscountRes;
import com.example.tracking_order.service.IUserOfDiscounService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user-of-discount")
@AllArgsConstructor
public class UserOfDiscountController {
    private final IUserOfDiscounService service;

    @PostMapping()
    public ResponseEntity<BaseResponse<UserOfDiscountRes>> create(@RequestBody @Valid UserOfDiscountReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(req)),  HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<UserOfDiscountRes>> update(@PathVariable UUID id,
                                                  @RequestBody @Valid UserOfDiscountReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.update(id, req)),   HttpStatus.OK);
    }
}
