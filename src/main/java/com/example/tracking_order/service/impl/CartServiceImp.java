package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.CartReq;
import com.example.tracking_order.dto.response.CartRes;
import com.example.tracking_order.entity.CartEntity;
import com.example.tracking_order.mapper.CartMapper;
import com.example.tracking_order.repository.CartRepository;
import com.example.tracking_order.service.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImp implements ICartService {
    private final CartRepository repo;
    private final CartMapper mapper;
    @Override
    public CartRes create(CartReq req) {
        CartEntity cart = mapper.fromCreate(req);
        repo.save(cart);
        return mapper.toResponse(cart);
    }
}
