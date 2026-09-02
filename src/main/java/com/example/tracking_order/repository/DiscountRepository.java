package com.example.tracking_order.repository;

import com.example.tracking_order.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, UUID> {
}
