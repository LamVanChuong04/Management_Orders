package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.UserOfDiscountReq;
import com.example.tracking_order.dto.response.UserOfDiscountRes;
import com.example.tracking_order.entity.UserOfDiscounteEntity;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.UserOfDiscountMapper;
import com.example.tracking_order.repository.UserOfDiscountRepository;
import com.example.tracking_order.service.IUserOfDiscounService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserOfDiscountServiceImp implements IUserOfDiscounService {
    private final UserOfDiscountRepository repo;
    private final UserOfDiscountMapper mapper;
    @Override
    @Transactional
    public UserOfDiscountRes create(UserOfDiscountReq req) {
        UserOfDiscounteEntity entity = mapper.fromCreate(req);
        repo.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public UserOfDiscountRes update(UUID id, UserOfDiscountReq req) {
        UserOfDiscounteEntity entity = repo.findById(id).orElseThrow(()->new ResourceNotfoundException());
        mapper.fromUpdate(req, entity);
        repo.save(entity);
        return mapper.toResponse(entity);
    }
}
