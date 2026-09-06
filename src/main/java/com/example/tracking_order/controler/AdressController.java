package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.AddressReq;
import com.example.tracking_order.dto.response.AddressRes;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.service.IAddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/address")
@AllArgsConstructor
public class AdressController {
    private final IAddressService service;

    @PostMapping()
    public ResponseEntity<BaseResponse<AddressRes>> create(@RequestBody @Valid AddressReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(req)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<AddressRes>> update(@PathVariable UUID id,
                                           @RequestBody @Valid AddressReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.update(id, req)), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<BaseResponse<AddressRes>> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findById(id)), HttpStatus.OK);
    }
}
