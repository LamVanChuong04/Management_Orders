package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.InventoryRequest;
import com.example.tracking_order.dto.response.InventoryResponse;
import com.example.tracking_order.entity.InventoryEntity;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.InventoryMapper;
import com.example.tracking_order.repository.InventoryRepository;
import com.example.tracking_order.service.IInventoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InventoryServiceImp implements IInventoryService {
    private final InventoryRepository repository;
    private final InventoryMapper mapper;

    @Override
    public List<InventoryResponse> getAllProductByWarehouseId(UUID id) {
        return List.of();
    }

    @Override
    @Transactional
    public InventoryResponse create(InventoryRequest req) {
        InventoryEntity entity = mapper.toEntity(req);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public InventoryResponse update(UUID id,InventoryRequest request) {
        InventoryEntity inventory = repository.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        InventoryEntity entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public void delete(UUID id) {
        InventoryEntity inventory = repository.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        inventory.setIsDeleted(true);
        repository.save(inventory);
    }
}
