package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.InventoryReq;
import com.example.tracking_order.dto.response.InventoryRes;
import com.example.tracking_order.entity.InventoryEntity;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.InventoryMapper;
import com.example.tracking_order.repository.InventoryRepository;
import com.example.tracking_order.service.IInventoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InventoryServiceImp implements IInventoryService {
    private final InventoryRepository repository;
    private final InventoryMapper mapper;

    @Override
    public Page<InventoryRes> getAllProductByWarehouseId(UUID id, Pageable pageable) {
        Page<InventoryEntity> list = repository.findByWarehouseId(id, pageable);
        return list.map(mapper::toResponse);
    }

    @Override
    @Transactional
    public InventoryRes create(InventoryReq req) {
        InventoryEntity entity = mapper.fromCreate(req);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public InventoryRes update(UUID id, InventoryReq request) {
        InventoryEntity inventory = repository.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        mapper.update(request,inventory);
        repository.save(inventory);
        return mapper.toResponse(inventory);
    }

    @Override
    public void delete(UUID id) {
        InventoryEntity inventory = repository.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        inventory.setIsDeleted(true);
        repository.save(inventory);
    }
}
