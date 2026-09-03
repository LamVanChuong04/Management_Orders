package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.UserRequest;
import com.example.tracking_order.dto.response.UserResponse;
import com.example.tracking_order.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface IUserService {
    UserResponse createUser(UserRequest req);
    UserResponse updateUser(UserRequest req, UUID userId);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(UUID id);
    Page<UserResponse> getAllUsers(Pageable pageable);
    void deleteUser(UUID id);
}
