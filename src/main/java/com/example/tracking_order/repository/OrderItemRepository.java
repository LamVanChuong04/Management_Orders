package com.example.tracking_order.repository;

import com.example.tracking_order.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, UUID> {
    List<OrderItemEntity> findByOrderId(UUID orderId);


    @Query("select o from OrderItemEntity o join fetch o.productVariant p join fetch p.product where o.id in :ids")
    List<OrderItemEntity> findAllByIds(List<UUID> ids);
}
