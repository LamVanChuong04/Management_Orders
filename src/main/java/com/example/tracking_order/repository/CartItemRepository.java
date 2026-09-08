package com.example.tracking_order.repository;

import com.example.tracking_order.entity.CartItemEntity;
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
}

