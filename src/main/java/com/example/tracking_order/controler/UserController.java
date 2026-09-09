package com.example.tracking_order.controler;

import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.request.ChangePassReq;
import com.example.tracking_order.dto.request.UserReq;
import com.example.tracking_order.dto.response.*;
import com.example.tracking_order.service.IDiscountService;
import com.example.tracking_order.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User Controller")
public class UserController {
    @Autowired
    private IUserService service;
    @Autowired
    private IDiscountService discountService;

    @Operation(method = "POST", summary = "Add new user", description = "Send a request via this API to create new user")
    @PostMapping()
    public ResponseEntity<BaseResponse<UserRes>> create(@Valid @RequestBody UserReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.createUser(req)), HttpStatus.CREATED);
    }
    @Operation(method = "PUT", summary = "Update user by id", description = "Send a request via this API to update user by id")
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<UserRes>> update(@PathVariable UUID id, @Valid @RequestBody UserReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.updateUser(req, id)), HttpStatus.ACCEPTED);
    }
    @Operation(method = "GET", summary = "Get all user", description = "Send a request via this API to all get user")
    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BaseResponse<List<UserRes>>> findAllUsers() {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getAllUsers()), HttpStatus.OK);
    }
    @Operation(method = "GET", summary = "Get user by id", description = "Send a request via this API to user by id")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserRes>> findUserById(@PathVariable UUID id) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getUserById(id)), HttpStatus.OK);
    }
    @Operation(method = "GET", summary = "Get all user and sort", description = "Send a request via this API to all get user")
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
    @Operation(method = "DELETE", summary = "Delete user by id", description = "Send a request via this API to delete user by id")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable UUID id) {
        service.deleteUser(id);
        return new ResponseEntity<>(BaseResponse.ofDeleteSuccess(), HttpStatus.OK);
    }
    // get all discounts for user
//    @Operation(method = "GET", summary = "Get all discount by user id", description = "Send a request via this API to get all discount by user id")
//    @GetMapping("/{id}/discount")
//    public ResponseEntity<BaseResponse<List<DiscountRes>>> getUserOfDiscount(@PathVariable UUID id) {
//        return new ResponseEntity<>(BaseResponse.ofSuccess(discountService.findAllByUserId(id)), HttpStatus.OK);
//    }
    // // lấy chi tiết giỏ hàng
//    @GetMapping("/{id}/cart")
//    public ResponseEntity<BaseResponse<List<CartItemRes>> getUserOfCart(@PathVariable UUID id) {
//
//    }
    @Operation(method = "POST", summary = "Change password for user", description = "Send a request via this API to change password for user")
    @PostMapping("/change-password")
    public ResponseEntity<BaseResponse<String>> changePassword(@Valid @RequestBody ChangePassReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.changePassword(req)), HttpStatus.OK);
    }

}
