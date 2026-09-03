package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.InventoryRequest;
import com.example.tracking_order.dto.response.InventoryResponse;
import com.example.tracking_order.entity.InventoryEntity;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.UUID;

public interface IInventoryService {
    List<InventoryResponse> getAllProductByWarehouseId(UUID id);
    InventoryResponse create(InventoryRequest req);
    InventoryResponse update(UUID id, InventoryRequest req);
    void delete(UUID id);

}
