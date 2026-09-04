package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.CartItemReq;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.entity.CartItemEntity;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.CartItemMapper;
import com.example.tracking_order.repository.CartItemRepository;
import com.example.tracking_order.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartItemServiceImp implements ICartItemService {
    @Autowired
    private CartItemRepository repo;
    @Autowired
    private CartItemMapper mapper;
    @Override
    public CartItemRes create(CartItemReq req) {
        CartItemEntity entity = mapper.fromCreate(req);
        repo.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public CartItemRes update(UUID id, CartItemReq req) {
        CartItemEntity entity = repo.findById(id).orElseThrow(()-> new ResourceNotfoundException());
        mapper.fromUpdate(req, entity);
        repo.save(entity);
        return mapper.toResponse(entity);
    }
}
