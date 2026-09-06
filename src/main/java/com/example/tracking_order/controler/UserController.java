package com.example.tracking_order.controler;

import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.request.ChangePassReq;
import com.example.tracking_order.dto.request.UserReq;
import com.example.tracking_order.dto.response.*;
import com.example.tracking_order.service.IDiscountService;
import com.example.tracking_order.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private IUserService service;
    @Autowired
    private IDiscountService discountService;

    @PostMapping()
    public ResponseEntity<BaseResponse<UserRes>> create(@Valid @RequestBody UserReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.createUser(req)), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<UserRes>> update(@PathVariable UUID id, @Valid @RequestBody UserReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.updateUser(req, id)), HttpStatus.ACCEPTED);
    }
    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BaseResponse<List<UserRes>>> findAllUsers() {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getAllUsers()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserRes>> findUserById(@PathVariable UUID id) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getUserById(id)), HttpStatus.OK);
    }
    @GetMapping("/getall")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BaseResponse<List<UserRes>>> findAllUser(@RequestParam int size,
                                                   @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getAllUsers(pageable)), HttpStatus.OK);
    }

    @GetMapping("/getallSort")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BaseResponse<List<UserRes>>> findAllUserAndSort(@RequestParam int size,
                                                          @RequestParam int page,
                                                          @RequestParam(defaultValue = "id") String sort,
                                                          @RequestParam(defaultValue = "desc") String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sortBy = Sort.by(sortDirection, sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getAllUsers(pageable)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable UUID id) {
        service.deleteUser(id);
        return new ResponseEntity<>(BaseResponse.ofDeleteSuccess(), HttpStatus.OK);
    }
    // get all discounts for user
    @GetMapping("/{id}/discount")
    public ResponseEntity<BaseResponse<List<DiscountRes>>> getUserOfDiscount(@PathVariable UUID id) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(discountService.findAllByUserId(id)), HttpStatus.OK);
    }
    // // lấy chi tiết giỏ hàng
//    @GetMapping("/{id}/cart")
//    public ResponseEntity<BaseResponse<List<CartItemRes>> getUserOfCart(@PathVariable UUID id) {
//
//    }
    @PostMapping("/change-password")
    public ResponseEntity<BaseResponse<String>> changePassword(@Valid @RequestBody ChangePassReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.changePassword(req)), HttpStatus.OK);
    }

}
