package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.WarehouseRequest;
import com.example.tracking_order.dto.response.WarehouseResponse;
import com.example.tracking_order.entity.WarehouseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    WarehouseEntity fromCreate(WarehouseRequest req);
    WarehouseResponse toResponse(WarehouseEntity entity);
    void fromUpdate(WarehouseRequest req, @MappingTarget WarehouseEntity warehouseEntity);
}
