package com.example.tracking_order.repository;

import com.example.tracking_order.entity.OrderReturnEntity;
import com.example.tracking_order.enums.ReturnStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface OrderReturnRepository extends JpaRepository<OrderReturnEntity, UUID> {
    @EntityGraph(attributePaths = {"user"})
    Page<OrderReturnEntity> findByReturnStatus(Pageable pageable, @Param("status") ReturnStatus status);

    @Query("select count(*) from OrderReturnEntity o where o.returnStatus = :status ")
    int countReturnStatus(@Param("status") ReturnStatus status);

    @Query("select sum(o.totalRefund) from OrderReturnEntity o")
    BigDecimal totalRefund();

    @Query("select count(*) from OrderReturnEntity ")
    int countAllReturns();

    @EntityGraph(attributePaths = {"user", "items", "items.orderItem", "items.orderItem.productVariant", "items.orderItem.productVariant.product"})
    Optional<OrderReturnEntity> findById(UUID id);

    @EntityGraph(attributePaths = {"user"})

    @Query("SELECT r FROM OrderReturnEntity r WHERE r.id > :lastId ORDER BY r.id ASC")
    List<OrderReturnEntity> findNextBatch(@Param("lastId") UUID lastId, Pageable pageable);

}
