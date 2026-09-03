package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.WarehouseRequest;
import com.example.tracking_order.dto.response.WarehouseResponse;
import com.example.tracking_order.entity.InventoryEntity;
import com.example.tracking_order.entity.WarehouseEntity;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.WarehouseMapper;
import com.example.tracking_order.repository.WarehouseRepository;
import com.example.tracking_order.service.IWarehouseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarehouseServiceImp implements IWarehouseService {
    private WarehouseRepository repository;
    private WarehouseMapper mapper;


    @Override
    @Transactional
    public WarehouseResponse create(WarehouseRequest req) {
        WarehouseEntity warehouse = mapper.fromCreate(req);
        repository.save(warehouse);
        return mapper.toResponse(warehouse);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        WarehouseEntity warehouse = repository.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        warehouse.setIsDeleted(true);
        repository.save(warehouse);
    }

    @Override
    @Transactional
    public WarehouseResponse update(UUID id, WarehouseRequest req) {
        WarehouseEntity warehouse = repository.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        mapper.fromUpdate(req, warehouse);
        repository.save(warehouse);
        return mapper.toResponse(warehouse);
    }

    @Override
    public Page<WarehouseResponse> findAll(Pageable  pageable) {
        Page<WarehouseEntity> warehouse = repository.findByIsDeletedFalse(pageable);
        return warehouse.map(mapper::toResponse);
    }

    @Override
    public WarehouseResponse findById(UUID id) {
        WarehouseEntity warehouse = repository.findById(id)
                .orElseThrow(()-> new ResourceNotfoundException());
        return mapper.toResponse(warehouse);
    }


}
