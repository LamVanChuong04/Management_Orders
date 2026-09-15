package com.example.tracking_order.repository;

import com.example.tracking_order.entity.OrderEntity;


import com.example.tracking_order.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    Page<OrderEntity> findByIsDeletedFalse(Pageable pageable);
    Page<OrderEntity> findByUserId(UUID userId, Pageable pageable);
    List<OrderEntity> findByUserId(UUID userId);
    @Query("select count(*) from OrderEntity o where o.status = 'FAILDED'")
    int countOrderFailed();

    @Query("select count(*) from OrderEntity o where o.status = 'DELIVERED' and o.user.id = :userId")
    int countOrderCompleted(UUID userId);
    @Query("select count(*) from OrderEntity o where o.status = 'PENDING' and o.user.id = :userId")
    int countOrderPending(UUID userId);

    @Query("select count(*) from OrderEntity o where o.status = 'PENDING'")
    int countOrderPending();
    @Query("select count(*) from OrderEntity o where o.status = 'SHIPPING'")
    int countOrderShipping();
    @Query("select count(*) from OrderEntity")
    int sumQuantityOrder();
    @Query("select sum(o.total) from OrderEntity o")
    BigDecimal totalRevenue();

    Page<OrderEntity> findAll(Pageable pageable);

    // find order with status SHIPPING or DELIVERED
    @Query("select o from OrderEntity o where o.status = :status")
    List<OrderEntity> findByStatus(@Param("status") OrderStatus status);

    @Query("select o from OrderEntity o where o.status = :status")
    Page<OrderEntity> findByOrderStatus(Pageable pageable, @Param("status") OrderStatus status);


    @Query("select count(o) from OrderEntity o " +
            "where o.status = 'PENDING' and date(o.createdAt) = :createdAt")
    int countOrderPendingToday(@Param("createdAt") LocalDate createdAt);

    @Query("select count(o) from OrderEntity o " +
            "where date(o.createdAt) = :createdAt")
    int countOrderToday(@Param("createdAt") LocalDate createdAt);

    @Query("select count(o) from OrderEntity o " +
            "where o.status = 'CONFIRMED' and date(o.createdAt) = :createdAt")
    int countOrderCompletedToday(@Param("createdAt") LocalDate createdAt);



    // return management
    @Query("select count(*) from OrderEntity o where o.status = 'RETURNING'")
    int countOrderRefund();
    @Query("select sum(o.total) from OrderEntity o where o.status = 'RETURNING'")
    BigDecimal totalRefund();

}
