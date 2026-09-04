package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.UserReq;
import com.example.tracking_order.dto.response.UserRes;
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
    private UserMapper mapper;

    @Override
    public UserRes createUser(UserReq req) {
        UserEntity userEntity = mapper.userDtoToUserEntity(req);
        userRespository.save(userEntity);
        return mapper.userEntityToUserResp(userEntity);
    }

    @Override
    @Transactional
    public UserRes updateUser(UserReq req, UUID userId) {
        UserEntity user = userRespository.findById(userId)
                .orElseThrow(()-> new ResourceNotfoundException());
        mapper.updateUserFromDto(req, user);
        userRespository.save(user);
        return mapper.userEntityToUserResp(user);
    }

    @Override
    public List<UserRes> getAllUsers() {
        return userRespository.findAll().stream().map(mapper::userEntityToUserResp)
                .collect(Collectors.toList());
    }

    @Override
    public UserRes getUserById(UUID id) {
        UserEntity user = userRespository.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        return mapper.userEntityToUserResp(user);
    }

    @Override
    public Page<UserRes> getAllUsers(Pageable pageable) {
        Page<UserEntity> page = userRespository.findAll(pageable);
        return page.map(mapper::userEntityToUserResp);
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        UserEntity user = userRespository.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        user.setIsDeleted(true);
        userRespository.save(user);
    }

}
