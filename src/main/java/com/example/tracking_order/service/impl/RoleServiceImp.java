package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.RoleRequest;
import com.example.tracking_order.dto.response.RoleResponse;
import com.example.tracking_order.entity.RoleEntity;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.RoleMapper;
import com.example.tracking_order.repository.RoleRepository;
import com.example.tracking_order.service.IRoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImp implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper mapper;

    @Override
    @Transactional
    public RoleResponse createRole(RoleRequest role) {
        RoleEntity roleEntity = mapper.roleReqToEntity(role);
        roleRepository.save(roleEntity);
        return mapper.entityToResponse(roleEntity);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream().map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteRole(UUID id) {
        RoleEntity role = roleRepository.findById(id).orElseThrow(()-> new ResourceNotfoundException());
        role.setIsDeleted(true);
        roleRepository.save(role);
    }
}
