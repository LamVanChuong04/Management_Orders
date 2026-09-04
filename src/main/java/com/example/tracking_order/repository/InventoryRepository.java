package com.example.tracking_order.repository;

import com.example.tracking_order.entity.InventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, UUID> {
    List<InventoryEntity> findByWarehouseId(UUID id);
    Page<InventoryEntity> findByWarehouseId(UUID id, Pageable pageable);
}
