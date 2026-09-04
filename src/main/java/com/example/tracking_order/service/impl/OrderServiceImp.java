package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.OrderReq;
import com.example.tracking_order.dto.response.OrderRes;
import com.example.tracking_order.entity.OrderEntity;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.OrderMapper;
import com.example.tracking_order.repository.OrderRepository;
import com.example.tracking_order.service.IOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImp implements IOrderService {
    @Autowired
    private OrderRepository repo;
    @Autowired
    private OrderMapper mapper;

    @Override
    @Transactional
    public OrderRes create(OrderReq req) {
        OrderEntity order = mapper.fromCreate(req);
        repo.save(order);
        return mapper.toResponse(order);
    }

    @Override
    @Transactional
    public OrderRes update(UUID id, OrderReq req) {
        OrderEntity order = repo.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        mapper.fromUpdate(req, order);
        repo.save(order);
        return mapper.toResponse(order);
    }

    @Override
    public Page<OrderRes> findAll(Pageable pageable) {
        Page<OrderEntity> orders = repo.findByIsDeletedFalse(pageable);
        return orders.map(mapper::toResponse);
    }

}
