package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.ProductVariantReq;
import com.example.tracking_order.dto.response.ProductVariantRes;
import com.example.tracking_order.entity.ProductVariantEntity;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.ProductVariantMapper;
import com.example.tracking_order.repository.ProductVariantRepository;
import com.example.tracking_order.service.IProductVariantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductVariantSerivceImp implements IProductVariantService {
    @Autowired
    private ProductVariantRepository repo;
    @Autowired
    private ProductVariantMapper mapper;

    @Override
    @Transactional
    public ProductVariantRes create(ProductVariantReq req) {
        ProductVariantEntity entity = mapper.fromCreate(req);
        repo.save(entity);
        return mapper.fromEntity(entity);
    }

    @Override
    @Transactional
    public ProductVariantRes update(UUID id, ProductVariantReq req) {
        ProductVariantEntity entity = repo.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        mapper.fromUpdate(req, entity);
        repo.save(entity);
        return mapper.fromEntity(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        ProductVariantEntity entity = repo.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        entity.setIsDeleted(true);
        repo.save(entity);
    }

    @Override
    public ProductVariantRes findById(UUID id) {
        ProductVariantEntity entity = repo.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        return mapper.fromEntity(entity);
    }
}
