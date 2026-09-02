package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.UserRoleRequest;
import com.example.tracking_order.entity.UserRoleEntity;
import com.example.tracking_order.mapper.UserRoleMapper;
import com.example.tracking_order.repository.UserRoleRepository;
import com.example.tracking_order.service.IUserRoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceIml implements IUserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional
    public void createUserRole(UserRoleRequest userRoleRequest) {
        UserRoleEntity userRole = userRoleMapper.toUserRoleRequest(userRoleRequest);
        userRoleRepository.save(userRole);
    }
}
