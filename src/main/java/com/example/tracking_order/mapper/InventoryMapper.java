package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.InventoryRequest;
import com.example.tracking_order.dto.response.InventoryResponse;
import com.example.tracking_order.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryEntity toEntity(InventoryRequest req);
    InventoryResponse toResponse(InventoryEntity entity);
    void update(InventoryRequest req, @MappingTarget InventoryEntity entity);
    List<InventoryResponse> toResponseList(List<InventoryEntity> list);
}
