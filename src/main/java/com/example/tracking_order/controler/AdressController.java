package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.AddressReq;
import com.example.tracking_order.dto.response.AddressRes;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.service.IAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Address Controller")
public class AdressController {
    private final IAddressService service;
    @Operation(method = "POST", summary = "Add new address", description = "Send a request via this API to add new address")
    @PostMapping()
    public ResponseEntity<BaseResponse<AddressRes>> create(@RequestBody @Valid AddressReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(req)), HttpStatus.CREATED);
    }
    @Operation(method = "PUT", summary = "Update address by id", description = "Send a request via this API to update address by id")
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<AddressRes>> update(@PathVariable UUID id,
                                           @RequestBody @Valid AddressReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.update(id, req)), HttpStatus.ACCEPTED);
    }
    @Operation(method = "GET", summary = "Get address by id", description = "Send a request via this API to get address by id")
    @GetMapping("/{id}")
    public  ResponseEntity<BaseResponse<AddressRes>> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findById(id)), HttpStatus.OK);
    }
}
