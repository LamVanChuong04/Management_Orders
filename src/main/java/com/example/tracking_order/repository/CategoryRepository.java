package com.example.tracking_order.repository;

import com.example.tracking_order.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
//    @Query("select c from CategoryEntity c where c.isDeleted = false")
//    List<CategoryEntity> findAll();

    List<CategoryEntity> findAllByIsDeletedFalse();
}
