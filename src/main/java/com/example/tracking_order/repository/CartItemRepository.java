package com.example.tracking_order.repository;

import com.example.tracking_order.entity.CartItemEntity;
import com.example.tracking_order.entity.ProductVariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {
    List<CartItemEntity> findByIsDeletedFalse();
    Optional<CartItemEntity> findByCartIdAndProductVariantId(UUID cartId, UUID productVariantId);
    Optional<CartItemEntity> findByCartId(UUID cartId);

    Optional<ProductVariantEntity> findByProductVariantId(UUID id);
    void deleteByProductVariantId(UUID id);
}

