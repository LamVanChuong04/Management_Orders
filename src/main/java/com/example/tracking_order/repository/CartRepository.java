package com.example.tracking_order.repository;

import com.example.tracking_order.entity.CartEntity;
import com.example.tracking_order.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CartRepository extends JpaRepository<CartEntity, UUID> {
    @Query("select c from CartEntity c join CartItemEntity ci on c.id = ci.cart.id")
    List<CartItemEntity> findAllByUserId(UUID userId);

    Optional<CartEntity> findByUserId(UUID userId);
}
