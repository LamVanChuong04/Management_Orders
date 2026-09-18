package com.example.tracking_order.repository;

import com.example.tracking_order.entity.ProductVariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariantEntity, UUID> {
    @Query("select count(*) from ProductVariantEntity p " +
            "join InventoryEntity i on p.id = i.productVariant.id " +
            "group by p.id having i.quantityInStock < 10")
    int countLowStock();

    @Query("select v from ProductVariantEntity v join fetch v.product where v.id in :ids")
    List<ProductVariantEntity> findAllByIds(List<UUID> ids);


}
