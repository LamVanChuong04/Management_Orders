package com.example.tracking_order.repository;

import com.example.tracking_order.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, UUID> {
    ShopEntity findByShopName(String shopName);
}
