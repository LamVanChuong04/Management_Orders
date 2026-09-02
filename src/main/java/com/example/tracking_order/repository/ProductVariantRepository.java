package com.example.tracking_order.repository;

import com.example.tracking_order.entity.ProductVariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariantEntity, UUID> {
}
