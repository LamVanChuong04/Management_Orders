package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.WarehouseReq;
import com.example.tracking_order.dto.response.WarehouseRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

public interface IWarehouseService {
    WarehouseRes create(WarehouseReq req);
    void delete(UUID id);
    WarehouseRes update(UUID id, WarehouseReq req);
    Page<WarehouseRes> findAll(Pageable pageable);
    WarehouseRes findById(UUID id);

}
