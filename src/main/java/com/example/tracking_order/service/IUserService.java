package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.UserReq;
import com.example.tracking_order.dto.response.UserRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface IUserService {
    UserRes createUser(UserReq req);
    UserRes updateUser(UserReq req, UUID userId);
    List<UserRes> getAllUsers();
    UserRes getUserById(UUID id);
    Page<UserRes> getAllUsers(Pageable pageable);
    void deleteUser(UUID id);
}
