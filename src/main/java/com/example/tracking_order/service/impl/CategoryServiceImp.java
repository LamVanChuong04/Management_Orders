package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.CategoryRequest;
import com.example.tracking_order.dto.response.CategoryResponse;
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
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements ICategoryService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private CategoryMapper mapper;

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest category) {
        CategoryEntity categoryEntity = mapper.toCategoryEntity(category);
        repository.save(categoryEntity);
        return mapper.toCategoryResponse(categoryEntity);
    }

    @Override
    public List<CategoryResponse> findAll() {
        return repository.findAllByIsDeletedFalse().stream()
                .map(mapper::toCategoryResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryResponse update(UUID id, CategoryRequest category) {
        CategoryEntity entity = repository.findById(id).orElseThrow(
                ()-> new ResourceNotfoundException()
        );
        mapper.updateCategory(category, entity);
        repository.save(entity);
        return mapper.toCategoryResponse(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        CategoryEntity category = repository.findById(id).orElseThrow(
                ()-> new ResourceNotfoundException());
        category.setIsDeleted(true);
        repository.save(category);
    }

    @Override
    public CategoryResponse findById(UUID id) {
        CategoryEntity category = repository.findById(id).orElseThrow(
                ()-> new ResourceNotfoundException());
        return mapper.toCategoryResponse(category);
    }
}
