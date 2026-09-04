package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.InventoryReq;
import com.example.tracking_order.dto.response.InventoryRes;
import com.example.tracking_order.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "productVariantId", target = "productVariant.id")
    InventoryEntity fromCreate(InventoryReq req);
    @Mapping(source = "productVariant.id", target = "productVariantId")
    InventoryRes toResponse(InventoryEntity entity);
    InventoryEntity update(InventoryReq req, @MappingTarget InventoryEntity entity);
    List<InventoryRes> toResponseList(List<InventoryEntity> list);
}
