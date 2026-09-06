package com.example.tracking_order.repository;

import com.example.tracking_order.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, UUID> {
    @Query("select d from DiscountEntity d join UserOfDiscounteEntity u on d.id = u.discount.id where u.user.id = :id")
    List<DiscountEntity> findAllByUserId(UUID id);
}
