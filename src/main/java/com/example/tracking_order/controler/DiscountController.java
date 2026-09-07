package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.DiscountReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.DiscountRes;
import com.example.tracking_order.service.IDiscountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/discounts")
@AllArgsConstructor
public class DiscountController {
    private final IDiscountService service;

    @PostMapping()
    //@PreAuthorize("hasRole('SELLER')")
    public ResponseEntity< BaseResponse<DiscountRes>> create(@RequestBody @Valid DiscountReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(req)),  HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('SELLER')")
    public ResponseEntity< BaseResponse<DiscountRes>> update(@PathVariable UUID id,
                                            @RequestBody @Valid DiscountReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.update(id, req)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity< BaseResponse<List<DiscountRes>>> findAll() {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findAll()), HttpStatus.OK);
    }
}
