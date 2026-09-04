package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.WarehouseReq;
import com.example.tracking_order.dto.response.WarehouseRes;
import com.example.tracking_order.entity.WarehouseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    WarehouseEntity fromCreate(WarehouseReq req);
    WarehouseRes toResponse(WarehouseEntity entity);
    void fromUpdate(WarehouseReq req, @MappingTarget WarehouseEntity warehouseEntity);
}
