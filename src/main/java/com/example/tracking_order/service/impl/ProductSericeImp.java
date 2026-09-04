package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.ProductReq;
import com.example.tracking_order.dto.response.ProductRes;
import com.example.tracking_order.entity.CategoryEntity;
import com.example.tracking_order.entity.ProductEntity;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.ProductMapper;
import com.example.tracking_order.repository.CategoryRepository;
import com.example.tracking_order.repository.ProductRepository;
import com.example.tracking_order.service.IProductService;
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
public class ProductSericeImp implements IProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductMapper mapper;


    @Override
    @Transactional
    public ProductEntity create(ProductReq request) {
        CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new ResourceNotfoundException());
        ProductEntity entity = mapper.toProductEntity(request);
        entity.setCategory(category);
        productRepository.save(entity);
        return entity;
    }

    @Override
    @Transactional
    public ProductEntity update(UUID id, ProductReq request) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotfoundException());
        mapper.updateProduct(request, entity);
        productRepository.save(entity);
        return entity;
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotfoundException());
        entity.setIsDeleted(true);
        productRepository.save(entity);
    }

    @Override
    public List<ProductRes> findAll() {
        return productRepository.findByIsDeletedFalse().stream().map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductRes> findAll(Pageable pageable) {
        Page<ProductEntity> page = productRepository.findByIsDeletedFalse(pageable);
        return page.map(mapper::toProductResponse);
    }
}
