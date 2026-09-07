package com.example.tracking_order.repository;

import com.example.tracking_order.entity.InventoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, UUID> {
    List<InventoryEntity> findByWarehouseId(UUID id);
    Page<InventoryEntity> findByWarehouseId(UUID id, Pageable pageable);
    Optional<InventoryEntity> findByProductVariantId(UUID id);

    @Modifying
    @Transactional
    @Query("update InventoryEntity i set i.quantityInStock = i.quantityInStock - :quantity " +
            "where i.quantityInStock >= :quantity and i.productVariant.id = :variantId")
    int updateStock(@Param("variantId") UUID variantId, @Param("quantity") Integer quantity);

}
