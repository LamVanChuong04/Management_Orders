package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.CategoryRequest;
import com.example.tracking_order.entity.CategoryEntity;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.CategoryMapper;
import com.example.tracking_order.repository.CategoryRepository;
import com.example.tracking_order.service.ICategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImp implements ICategoryService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private CategoryMapper mapper;

    @Override
    @Transactional
    public CategoryEntity create(CategoryRequest category) {
        CategoryEntity categoryEntity = mapper.toCategoryEntity(category);
        repository.save(categoryEntity);
        return categoryEntity;
    }

    @Override
    public List<CategoryEntity> findAll() {
        return repository.findAllByIsDeletedFalse();
    }

    @Override
    @Transactional
    public CategoryEntity update(UUID id, CategoryRequest category) {
        CategoryEntity entity = repository.findById(id).orElseThrow(
                ()-> new ResourceNotfoundException()
        );
        mapper.updateCategory(category, entity);
        repository.save(entity);
        return entity;
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        CategoryEntity category = repository.findById(id).orElseThrow(
                ()-> new ResourceNotfoundException());
        category.setIsDeleted(true);
        repository.save(category);
    }
}
