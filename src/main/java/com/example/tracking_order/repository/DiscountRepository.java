package com.example.tracking_order.repository;

import com.example.tracking_order.entity.DiscountEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, UUID> {
    @Modifying
    @Transactional
    @Query("update DiscountEntity d set d.quantity = d.quantity - 1 " +
            "where d.id = :id and d.quantity > 0 and d.status = 'ACTIVE'")
    int updateQuantity(@Param("id") UUID id);


    @Query("SELECT d FROM DiscountEntity d " +
            "WHERE d.id = :id AND d.status = 'ACTIVE' AND d.quantity > 0")
    Optional<DiscountEntity> findActiveById(@Param("id") UUID id);
}
