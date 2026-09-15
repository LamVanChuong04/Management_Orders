package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.ProductReq;
import com.example.tracking_order.dto.response.ProductDetailRes;
import com.example.tracking_order.entity.CategoryEntity;
import com.example.tracking_order.entity.ProductEntity;
import com.example.tracking_order.exception.BusinessException;
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
    public ProductDetailRes create(ProductReq request) {
        CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new ResourceNotfoundException());
        ProductEntity entity = mapper.toProductEntity(request);
        entity.setCategory(category);
        productRepository.save(entity);
        return mapper.toProductResponse(entity);
    }

    @Override
    @Transactional
    public ProductDetailRes update(UUID id, ProductReq request) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotfoundException());
        mapper.updateProduct(request, entity);
        productRepository.save(entity);
        return mapper.toProductResponse(entity);
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
    public List<ProductDetailRes> findAll() {
        return productRepository.findByIsDeletedFalse().stream().map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDetailRes> findAll(Pageable pageable) {
        Page<ProductEntity> page = productRepository.findByIsDeletedFalse(pageable);
        return page.map(mapper::toProductResponse);
    }

    @Override
    public ProductDetailRes findById(UUID id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(()-> new BusinessException("KHONG TIM THAY SAN PHAM"));
        return mapper.toProductResponse(product);
    }

    @Override
    public List<ProductDetailRes> findByCategoryId(UUID id) {
        List<ProductEntity> products = productRepository.findByCategoryId(id);
        return products.stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }
}
