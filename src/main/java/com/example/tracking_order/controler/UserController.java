package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.UserRequest;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.UserResponse;
import com.example.tracking_order.entity.UserEntity;
import com.example.tracking_order.mapper.UserMapper;
import com.example.tracking_order.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private IUserService service;

    @PostMapping()
    public BaseResponse<UserResponse> addUser(@Valid @RequestBody UserRequest req) {
        return BaseResponse.ofSuccess(service.createUser(req));
    }
    @PutMapping("/{id}")
    public BaseResponse<UserResponse> updateUser(@PathVariable UUID id, @Valid @RequestBody UserRequest req) {
        return BaseResponse.ofSuccess(service.updateUser(req, id));
    }
    @GetMapping()
    public BaseResponse<List<UserResponse>> findAllUsers() {
        return BaseResponse.ofSuccess(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public BaseResponse<UserResponse> findUserById(@PathVariable UUID id) {
        return BaseResponse.ofSuccess(service.getUserById(id));
    }
    @GetMapping("/getall")
    public BaseResponse<List<UserResponse>> findAllUser(@RequestParam int size,
                                                          @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, size);
        return BaseResponse.ofSuccess(service.getAllUsers(pageable));
    }

    @GetMapping("/getallSort")
    public BaseResponse<List<UserResponse>> findAllUserAndSort(@RequestParam int size,
                                                               @RequestParam int page,
                                                               @RequestParam(defaultValue = "id") String sort,
                                                               @RequestParam(defaultValue = "desc") String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sortBy = Sort.by(sortDirection, sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        return BaseResponse.ofSuccess(service.getAllUsers(pageable));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> delete(@PathVariable UUID id) {
        service.deleteUser(id);
        return BaseResponse.ofDeleteSuccess();
    }

}
