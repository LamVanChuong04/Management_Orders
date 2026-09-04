package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.UserReq;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.UserRes;
import com.example.tracking_order.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BaseResponse<UserRes> addUser(@Valid @RequestBody UserReq req) {
        return BaseResponse.ofSuccess(service.createUser(req));
    }
    @PutMapping("/{id}")
    public BaseResponse<UserRes> updateUser(@PathVariable UUID id, @Valid @RequestBody UserReq req) {
        return BaseResponse.ofSuccess(service.updateUser(req, id));
    }
    @GetMapping()
    public BaseResponse<List<UserRes>> findAllUsers() {
        return BaseResponse.ofSuccess(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public BaseResponse<UserRes> findUserById(@PathVariable UUID id) {
        return BaseResponse.ofSuccess(service.getUserById(id));
    }
    @GetMapping("/getall")
    public BaseResponse<List<UserRes>> findAllUser(@RequestParam int size,
                                                   @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, size);
        return BaseResponse.ofSuccess(service.getAllUsers(pageable));
    }

    @GetMapping("/getallSort")
    public BaseResponse<List<UserRes>> findAllUserAndSort(@RequestParam int size,
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
