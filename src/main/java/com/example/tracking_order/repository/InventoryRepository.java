package com.example.tracking_order.repository;

import com.example.tracking_order.entity.InventoryEntity;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, UUID> {
    Page<InventoryEntity> findByWarehouseId(UUID id, Pageable pageable);
    Optional<InventoryEntity> findByProductVariantId(UUID id);

    @Query("select count(p.id) from ProductVariantEntity p " +
            "join InventoryEntity i on p.id = i.productVariant.id " +
            "group by p.id having sum(i.quantityInStock) < 10")
    int countLowStock();
    @Query("select sum(i.quantityInStock) from InventoryEntity i")
    int sumProductVariant();

    @Query("select sum(i.quantityInStock * p.price) from InventoryEntity i " +
            "join ProductVariantEntity p on i.productVariant.id = p.id")
    BigDecimal sumPriceStock();

    // pessimistic lock
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select i from InventoryEntity i where i.productVariant.id in :variantIds")
    List<InventoryEntity> findByProductVariantIdIn(List<UUID> variantIds);
    // atomic update
    // Kho validation khi xay ra loi
    @Modifying
    @Transactional
    @Query("update InventoryEntity i set i.quantityInStock = i.quantityInStock - :quantity " +
            "where i.quantityInStock >= :quantity and i.productVariant.id = :variantId")
    int updateStock(@Param("variantId") UUID variantId, @Param("quantity") Integer quantity);



}
