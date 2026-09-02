package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.UserRequest;
import com.example.tracking_order.dto.response.UserResponse;
import com.example.tracking_order.entity.UserEntity;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.UserMapper;
import com.example.tracking_order.repository.UserRespository;
import com.example.tracking_order.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImp implements IUserService {
    private UserRespository userRespository;
    private UserMapper userMapper;

    @Override
    public UserEntity createUser(UserRequest userRequest) {
        UserEntity userEntity = userMapper.userDtoToUserEntity(userRequest);
        userRespository.save(userEntity);
        return userEntity;
    }

    @Override
    @Transactional
    public UserEntity updateUser(UserRequest userRequest, UUID userId) {
        UserEntity user = userRespository.findById(userId)
                .orElseThrow(()-> new ResourceNotfoundException());
        userMapper.updateUserFromDto(userRequest, user);

        return userRespository.save(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRespository.findAll().stream().map(userMapper::userEntityToUserResp)
                .collect(Collectors.toList());
    }

    @Override
    public UserEntity getUserById(UUID id) {
        UserEntity user = userRespository.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        return user;
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        Page<UserEntity> page = userRespository.findAll(pageable);
        return page.map(userMapper::userEntityToUserResp);
    }

}
