package com.example.tracking_order.repository;

import com.example.tracking_order.entity.OrderEntity;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    Page<OrderEntity> findByIsDeletedFalse(Pageable pageable);
}
