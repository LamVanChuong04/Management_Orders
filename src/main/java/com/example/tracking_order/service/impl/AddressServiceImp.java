package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.AddressReq;
import com.example.tracking_order.dto.response.AddressRes;
import com.example.tracking_order.entity.AddressEntity;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.AddressMapper;
import com.example.tracking_order.repository.AddressRepository;
import com.example.tracking_order.service.IAddressService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressServiceImp implements IAddressService {
    @Autowired
    private AddressRepository repo;
    @Autowired
    private AddressMapper mapper;

    @Override
    @Transactional
    public AddressRes create(AddressReq req) {
        AddressEntity entity = mapper.fromCreate(req);
        repo.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public AddressRes update(UUID id, AddressReq req) {
        AddressEntity entity = repo.findById(id).orElseThrow(()-> new ResourceNotfoundException());
        mapper.fromUpdate(req, entity);
        repo.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        AddressEntity entity = repo.findById(id).orElseThrow(()-> new ResourceNotfoundException());
        entity.setIsDeleted(true);
        repo.save(entity);
    }

    @Override
    public AddressRes findById(UUID id) {
        AddressEntity entity = repo.findById(id).orElseThrow(()-> new ResourceNotfoundException());
        return mapper.toResponse(entity);
    }
}
