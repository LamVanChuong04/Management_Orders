package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.ChangePassReq;
import com.example.tracking_order.dto.request.UserReq;
import com.example.tracking_order.dto.response.UserRes;
import com.example.tracking_order.entity.UserEntity;
import com.example.tracking_order.enums.Role;
import com.example.tracking_order.exception.BusinessException;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.UserMapper;
import com.example.tracking_order.repository.UserRepository;
import com.example.tracking_order.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImp implements IUserService {
    private UserRepository userRepository;
    private UserMapper mapper;
    private PasswordEncoder encoder;

    @Override
    @Transactional
    public UserRes createUser(UserReq req) {
        UserEntity user = userRepository.findByUsername(req.getUserName());
        if(user != null){
            throw new BusinessException("Username already exists");
        }
        UserEntity userEntity = mapper.userDtoToUserEntity(req);
        userEntity.setPassword(encoder.encode(req.getPassword()));
        userEntity.setRole(Role.CUSTOMER);
        userRepository.save(userEntity);
        return mapper.userEntityToUserResp(userEntity);
    }

    @Override
    @Transactional
    public UserRes updateUser(UserReq req, UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotfoundException());
        mapper.updateUserFromDto(req, user);
        user.setPassword(encoder.encode(req.getPassword()));
        userRepository.save(user);
        return mapper.userEntityToUserResp(user);
    }

    @Override
    public List<UserRes> getAllUsers() {
        return userRepository.findAll().stream().map(mapper::userEntityToUserResp)
                .collect(Collectors.toList());
    }

    @Override
    public UserRes getUserById(UUID id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        return mapper.userEntityToUserResp(user);
    }

    @Override
    public Page<UserRes> getAllUsers(Pageable pageable) {
        Page<UserEntity> page = userRepository.findAll(pageable);
        return page.map(mapper::userEntityToUserResp);
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public String changePassword(ChangePassReq req) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(username);
        if(!encoder.matches(req.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password not correct");
        }
        user.setPassword(encoder.encode(req.getNewPassword()));
        userRepository.save(user);
        return "Changed password successfully";
    }

}
