package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.WarehouseRequest;
import com.example.tracking_order.dto.response.WarehouseResponse;
import com.example.tracking_order.entity.InventoryEntity;
import com.example.tracking_order.entity.WarehouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

public interface IWarehouseService {
    WarehouseResponse create(WarehouseRequest req);
    void delete(UUID id);
    WarehouseResponse update(UUID id, WarehouseRequest req);
    Page<WarehouseResponse> findAll(Pageable pageable);
    WarehouseResponse findById(UUID id);

}
