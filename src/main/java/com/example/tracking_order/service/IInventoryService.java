package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.InventoryReq;
import com.example.tracking_order.dto.response.InventoryRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IInventoryService {
    Page<InventoryRes> getAllProductByWarehouseId(UUID id, Pageable pageable);
    InventoryRes create(InventoryReq req);
    InventoryRes update(UUID id, InventoryReq req);
    void delete(UUID id);

}
