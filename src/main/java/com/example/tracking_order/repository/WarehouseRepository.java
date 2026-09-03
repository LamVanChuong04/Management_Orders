package com.example.tracking_order.repository;

import com.example.tracking_order.entity.InventoryEntity;
import com.example.tracking_order.entity.WarehouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, UUID> {
    //List<WarehouseEntity> findAllIsDeletedFalse(Pageable pageable);
    Page<WarehouseEntity> findByIsDeletedFalse(Pageable pageable);

}
