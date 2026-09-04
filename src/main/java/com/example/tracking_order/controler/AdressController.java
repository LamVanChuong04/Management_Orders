package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.AddressReq;
import com.example.tracking_order.dto.response.AddressRes;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.entity.BaseEntity;
import com.example.tracking_order.service.IAddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/address")
@AllArgsConstructor
public class AdressController {
    private final IAddressService service;

    @PostMapping()
    public BaseResponse<AddressRes> create(@RequestBody @Valid AddressReq req) {
        return BaseResponse.ofSuccess(service.create(req));
    }

    @PutMapping("/{id}")
    public BaseResponse<AddressRes> update(@PathVariable UUID id,
                                           @RequestBody @Valid AddressReq req) {
        return BaseResponse.ofSuccess(service.update(id, req));
    }

    @GetMapping("/{id}")
    public BaseResponse<AddressRes> getById(@PathVariable UUID id) {
        return BaseResponse.ofSuccess(service.findById(id));
    }
}
