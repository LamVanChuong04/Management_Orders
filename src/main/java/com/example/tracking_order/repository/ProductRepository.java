package com.example.tracking_order.repository;

import com.example.tracking_order.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findByIsDeletedFalse();
    Page<ProductEntity> findByIsDeletedFalse(Pageable pageable);
}
